package com.theoryx.xseed.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.theoryx.xseed.annotation.Link;
import com.theoryx.xseed.dto.UrlLabelMapping;
import com.theoryx.xseed.dto.StartupDTO;
import com.theoryx.xseed.dto.SurveyDTO;
import com.theoryx.xseed.dto.SurveyQuestionDTO;
import com.theoryx.xseed.dto.UserDTO;
import com.theoryx.xseed.model.AnswerOption;
import com.theoryx.xseed.model.Error;
import com.theoryx.xseed.model.Question;
import com.theoryx.xseed.model.QuestionAnswerType;
import com.theoryx.xseed.model.SnapshotLine;
import com.theoryx.xseed.model.Startup;
import com.theoryx.xseed.model.Survey;
import com.theoryx.xseed.model.SurveyQuestion;
import com.theoryx.xseed.model.User;
import com.theoryx.xseed.service.AnswerOptionService;
import com.theoryx.xseed.service.SnapshotService;
import com.theoryx.xseed.service.StartupService;
import com.theoryx.xseed.service.SurveyQuestionService;
import com.theoryx.xseed.service.SurveyService;
import com.theoryx.xseed.service.UserService;
import com.theoryx.xseed.utils.DateUtils;
import com.theoryx.xseed.utils.ValidationUtils;

@Controller
public class SurveyController {

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private SurveyService surveyService;
	@Autowired
	private UserService userService;
	@Autowired
	private StartupService startupService;
	@Autowired
	private SnapshotService snapshotService;
	@Autowired
	private SurveyQuestionService surveyQuestionService;
	@Autowired
	private AnswerOptionService answerOptionService;

	/**
	 * This method show all available surveys.
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @return "surveys" String
	 */
	@Link(label = "Surveys", parent = "Home", url = "/surveys")
	@RequestMapping(value = { "/surveys" }, method = RequestMethod.GET)
	public String getSurveysPage(HttpServletRequest request, Model model) {
		if (request.getSession().getAttribute("currentUser") != null) {
			List<SurveyDTO> surveys = surveyService.getAllSurveyDtos();
			model.addAttribute("surveys", surveys);
			request.getAttribute("breadCrumbs");
			model.addAttribute("breadcrumbs", request.getAttribute("breadCrumbs"));
			return "surveys";
		} else {
			return "redirect:/login";
		}
	}

	/**
	 * This method loads a survey by its id
	 * 
	 * @param id
	 *            This is the id of the survey which will be displayed
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @return This returns a survey by an id.
	 */
	@Link(label = "<SurveyName>", parent = "Surveys", url = "/startsurvey/survey?id=<id>")
	@RequestMapping(value = { "/startsurvey/survey" }, method = RequestMethod.GET)
	public String getSurveyPage(HttpServletRequest request, Model model, @RequestParam("id") Integer surveyId) {
		if (request.getSession().getAttribute("currentUser") != null) {
			Survey survey = surveyService.getById(surveyId);
			SurveyDTO surveyDto = surveyService.convertSurveyToSurveyDTO(survey);
			if (surveyDto.getNumberOfQuestions() == 0) {
				List<Error> errors = new ArrayList<Error>();
				errors.add(new Error(
						messageSource.getMessage("error.survey-no-questions", new Object[0], Locale.getDefault())));
				model.addAttribute("errors", errors);
				List<SurveyDTO> surveys = surveyService.getAllSurveyDtos();
				model.addAttribute("surveys", surveys);
				return "surveys";
			} else {
				model.addAttribute("currentSurvey", surveyDto);
				List<SurveyQuestion> surveyQuestions = survey.getQuestions();
				List<List<SurveyQuestionDTO>> surveyQuestionDtosPaged = surveyQuestionService
						.getSortedDtosByPage(surveyQuestions);
				model.addAttribute("surveyQuestionDtosPaged", surveyQuestionDtosPaged);
				String snapshotName = DateUtils.getCurrentDateAsName();
				model.addAttribute("snapshotName", snapshotName);
				model.addAttribute("surveyQuestionsDTOSNumberOfPages", surveyQuestionDtosPaged.size());

				@SuppressWarnings("unchecked")
				LinkedList<UrlLabelMapping> mappings = (LinkedList<UrlLabelMapping>) request
						.getAttribute("breadCrumbs");
				mappings.getLast().setLabel(surveyDto.getName());
				mappings.getLast().setUrl("/startsurvey/survey?id=" + surveyDto.getId());
				model.addAttribute("breadcrumbs", mappings);

				return "startsurvey";
			}
		} else {
			return "redirect:/login";
		}

	}

	/**
	 * This method is used when the request for saving a survey is sent
	 * 
	 * @param id
	 *            The survey id
	 * @param request
	 * @param model
	 * @return String - /createsnapshot view
	 */
	@RequestMapping(value = { "/submit/survey" }, method = RequestMethod.POST)
	public String submitSurvey(HttpServletRequest request, Model model, @RequestParam("id") Integer surveyId) {
		Survey survey = surveyService.getById(surveyId);
		List<SurveyQuestion> surveyQuestions = survey.getQuestions();
		surveyQuestions = surveyQuestionService.sortByPageAndPosition(surveyQuestions);
		List<SnapshotLine> snapshotLines = createSnapshotLines(request, model, surveyQuestions);
		if (snapshotLines == null) {
			model.addAttribute("submitError",
					messageSource.getMessage("error.submit", new Object[0], Locale.getDefault()));
		} else {
			String snapshotName = request.getParameter("snapshotName");
			snapshotName = ValidationUtils.validateSurveyName(snapshotName);
			User user = userService.convertUserDTOToUser((UserDTO) request.getSession().getAttribute("currentUser"));
			Startup startup = startupService
					.convertStartupDTOToStartup((StartupDTO) request.getSession().getAttribute("currentStartup"));

			snapshotService.save(snapshotName, survey, user, startup, snapshotLines);
			model.addAttribute("success",
					messageSource.getMessage("success.snapshot-saved", new Object[0], Locale.getDefault()));
		}
		return "redirect:/snapshots";
	}

	/**
	 * This method creates list of snapshotLines from survey questions-answers
	 * 
	 * @param request
	 * @param model
	 * @param surveyQuestions
	 * @return
	 */
	private List<SnapshotLine> createSnapshotLines(HttpServletRequest request, Model model,
			List<SurveyQuestion> surveyQuestions) {
		List<SnapshotLine> snapshotLines = new ArrayList<SnapshotLine>();
		for (SurveyQuestion surveyQuestion : surveyQuestions) {
			Question question = surveyQuestion.getQuestion();
			String type = question.getCategory().getType().name();
			
			if (type.equals(QuestionAnswerType.SINGLE_CHOICE.name())) {
				if (!addSingleOptionAnswerToSnapshotLine(snapshotLines, question, request)) {
					return null;
				}
			}
			if (type.equals(QuestionAnswerType.TEXT.name())) {
				if (!addTextAnswerToSnapshotLine(snapshotLines, question, request)) {;
					return null;
				}
			}
			if (type.equals(QuestionAnswerType.MULTIPLE_CHOICE.name())) {
				if (!addMultipleOptionAnswerToSnapshotLine(snapshotLines, question, request)) {
					return null;
				}
			}
			if (type.equals(QuestionAnswerType.YES_NO.name())) {
				if (!addYesNoAnswerToSnapshotLine(snapshotLines, question, request)) {
					return null;
				}
			}
		}
		return snapshotLines;
	}

	/**
	 * This method adds single option answer to the questionAnswer map
	 * 
	 * @param questionAnswer
	 * @param question
	 * @param id
	 * @param request
	 * @return Boolean
	 */
	private boolean addSingleOptionAnswerToSnapshotLine(List<SnapshotLine> snapshotLines, Question question,
			HttpServletRequest request) {
		Integer id = question.getId();
		String value = request.getParameter("radio_" + id);
		if (value != null) {
			SnapshotLine line = new SnapshotLine();
			Integer answerId = Integer.valueOf(value);
			AnswerOption answer = answerOptionService.findById(answerId);
			line.setQuestion(question);
			line.setSelected_answer(answer);
			String textValue = "";
			if (question.isHasOther()) {
				if (answerOptionService.isOtherChecked(value)) {
					textValue = request.getParameter("radio_" + id + "_other");
					if (textValue != null) {
						textValue = textValue.trim();
						if (textValue.length() > 0 && textValue.length() <= 100) {
							line.setTextResponse(textValue);
						} else {
							return false;
						}
					} else {
						return false;
					}
				}
			}
			snapshotLines.add(line);
			return true;
		}
		return false;
	}

	/**
	 * This method adds text answer to the questionAnswer map
	 * 
	 * @param questionAnswer
	 *            This is the map for questions and selected answers.
	 * @param id
	 *            This is question's id
	 * @request
	 */
	private boolean addTextAnswerToSnapshotLine(List<SnapshotLine> snapshotLines, Question question,
			HttpServletRequest request) {
		Integer id = question.getId();
		String value = request.getParameter("text_" + id);
		if (value == null) {
			value = "";
		}
		SnapshotLine line = new SnapshotLine();
		line.setQuestion(question);
		line.setTextResponse(value);
		snapshotLines.add(line);
		return true;
	}

	/**
	 * This method adds yes-no answer to the questionAnswer map
	 * 
	 * @param questionAnswer
	 *            This is the map for questions and selected answers.
	 * @param id
	 *            This is question's id
	 * @request
	 */
	private boolean addYesNoAnswerToSnapshotLine(List<SnapshotLine> snapshotLines, Question question,
			HttpServletRequest request) {
		Integer id = question.getId();
		String value = request.getParameter("yesno_" + id);
		SnapshotLine line = new SnapshotLine();
		line.setQuestion(question);
		AnswerOption answer = null;
		if (value == null) {
			answer = answerOptionService.findByText("FALSE");
		} else {
			answer = answerOptionService.findByText("TRUE");
		}
		line.setSelected_answer(answer);
		snapshotLines.add(line);
		return true;
	}

	/**
	 * This method adds multiple option answer to the questionAnswer map
	 * 
	 * @param questionAnswer
	 *            This is the map for questions and selected answers.
	 * @param id
	 *            This is question's id
	 * @param surveyQuestion
	 *            This is a question in the survey
	 * @param request
	 */
	private boolean addMultipleOptionAnswerToSnapshotLine(List<SnapshotLine> snapshotLines, Question question,
			HttpServletRequest request) {
		List<AnswerOption> answerOptions = new ArrayList<AnswerOption>();
		Integer id = question.getId();
		SnapshotLine line = new SnapshotLine();
		line.setQuestion(question);

		for (AnswerOption answerOption : question.getAnswergroup().getOptions()) {
			Integer answerOptionId = answerOption.getId();
			String value = request.getParameter("multiple_" + id + "_" + answerOptionId);
			if (value != null) {
				if (answerOptionService.isOtherChecked(answerOption.getId().toString())) {
					String textValue = request.getParameter("multiple_" + id + "_other");
					if (textValue != null) {
						textValue = textValue.trim();
						if (textValue.length() > 0 && textValue.length() <= 100) {
							line.setTextResponse(textValue);
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else {
					answerOptions.add(answerOption);
				}
			}
		}

		if (answerOptions.isEmpty()) {
			return false;
		} else {
			AnswerOption[] array = new AnswerOption[answerOptions.size()];
			line.setMultipleSelectedAnswers(answerOptions.toArray(array));
			snapshotLines.add(line);
			return true;
		}
	}

}
