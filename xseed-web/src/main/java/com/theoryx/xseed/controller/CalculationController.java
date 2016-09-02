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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.theoryx.xseed.dto.AjaxQuestion;
import com.theoryx.xseed.annotation.Link;
import com.theoryx.xseed.dto.CalculationDTO;
import com.theoryx.xseed.dto.GroupCalculationDTO;
import com.theoryx.xseed.dto.UrlLabelMapping;
import com.theoryx.xseed.dto.QuestionCalculationDTO;
import com.theoryx.xseed.dto.QuestionDTO;
import com.theoryx.xseed.dto.StartupCalculationDTO;
import com.theoryx.xseed.model.AnswerOption;
import com.theoryx.xseed.model.Calculation;
import com.theoryx.xseed.model.Question;
import com.theoryx.xseed.model.QuestionCalculation;
import com.theoryx.xseed.model.Snapshot;
import com.theoryx.xseed.model.SnapshotLine;
import com.theoryx.xseed.service.CalculationService;
import com.theoryx.xseed.service.GroupCalculationService;
import com.theoryx.xseed.service.QuestionCalculationService;
import com.theoryx.xseed.service.QuestionService;
import com.theoryx.xseed.service.SnapshotService;
import com.theoryx.xseed.service.SnapshotlineService;
import com.theoryx.xseed.service.StartupCalculationService;
import com.theoryx.xseed.utils.DateUtils;

@Controller
public class CalculationController {

	
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private SnapshotService snapshotService;
	@Autowired
	private StartupCalculationService startupCalculationService;
	@Autowired
	private CalculationService calculationService;
	@Autowired
	private GroupCalculationService groupCalculationService;
	@Autowired
	private SnapshotlineService snapshotlineService;
	@Autowired
	private QuestionCalculationService questionCalculationService;

	/**
	 * This method shows the preCalculation options.
	 * 
	 * @param request
	 * @param model
	 * @return "calculate" String
	 */
	@Link(label = "Calculate", parent = "Calculations", url = "/calculate")
	@RequestMapping(value = { "/calculate" }, method = RequestMethod.GET)
	public String getCalculationPage(HttpServletRequest request, Model model) {
		if (request.getSession().getAttribute("currentUser") != null) {
			List<Question> filterQuestions = questionService.findByFilter(true);
			List<Question> kpiQuestions = questionService.findByKpi(true);
			List<QuestionDTO> filterQuestionDtos = questionService.convert(filterQuestions);
			List<QuestionDTO> kpiQuestionDtos = questionService.convert(kpiQuestions);

			model.addAttribute("filterQuestions", filterQuestionDtos);
			model.addAttribute("kpiQuestions", kpiQuestionDtos);
			model.addAttribute("breadcrumbs", request.getAttribute("breadCrumbs"));
			String name = DateUtils.getCurrentDateDefaultName();
			model.addAttribute("defaultName", name);
			return "calculate";
		} else {
			return "index";
		}
	}

	/**
	 * This method is used when the request for making a calculation is sent
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return "calculation-summary" String
	 */
	@RequestMapping(value = { "/calculate" }, method = RequestMethod.POST)
	public String filter(HttpServletRequest request, Model model) {

		String calculationName = request.getParameter("calculationName");
		//Getting the filter questions
		List<Question> filterQuestions = questionService.findByFilter(true);
		//SnapshotLines that will be used to check database snapshotLines for same answers to filter questions
		List<SnapshotLine> snapshotLines = createFilterSnapshotlines(request, filterQuestions);

		Question kpiQuestion = null;
		boolean isKpiSelected = false;
		String value = request.getParameter("kpi");
		if (value != null) {
			kpiQuestion = questionService.findbyId(Integer.valueOf(value));
			isKpiSelected = true;
		}
		if (snapshotLines.isEmpty() == true || isKpiSelected == false) {
			model.addAttribute("noResultsFound", messageSource.getMessage("message.filter", new Object[0], Locale.getDefault()));
			return "calculation-summary";
		}
		//Filtering the snapshots based on snapshotLines
		List<Snapshot> filteredSnapshots = snapshotService.filterSnapshots(filterQuestions, snapshotLines);
		if (filteredSnapshots.isEmpty() || filteredSnapshots == null) {
			model.addAttribute("noResultsFound", messageSource.getMessage("message.no-startups-found", new Object[0], Locale.getDefault()));
			return "calculation-summary";
		}
		//Making all the calculations and persisting in database
		Calculation calculation = calculationService.calculate(filteredSnapshots, kpiQuestion, calculationName);
		CalculationDTO calculationDTO = calculationService.convertCalculationToCalculationDTO(calculation);
		model.addAttribute("calculation", calculationDTO);
		return "calculation-summary";
	}
	
	/**
	 * This method shows the details for a chosen calculation
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return "calculation-details" String
	 */
	@Link(label = "Details", parent = "Calculations", url = "/calculation-details")
	@RequestMapping(value = { "/calculation-details" }, method = RequestMethod.GET)
	public String getDetails(HttpServletRequest request, Model model,
			@RequestParam("calculationid") String calculationId) {
		Integer id = Integer.parseInt(calculationId);
		Calculation calculation = calculationService.findById(id);
		CalculationDTO calculationDto = calculationService.convertCalculationToCalculationDTO(calculation);

		List<List<List<QuestionCalculation>>> questionCalculationDtos = questionCalculationService
				.groupByAnswerGroupAndQuestion(calculation.getQuestionCalculations());
		List<List<QuestionCalculationDTO>> groupedQuestionCalculationDtos = questionCalculationService.convert(questionCalculationDtos);		
		List<GroupCalculationDTO> groupCalculationDtos = groupCalculationService.convert(calculation.getGroupCalculations());		
		List<StartupCalculationDTO> startupCalculationDtos = startupCalculationService.convert(calculation.getStartupCalculations());

		model.addAttribute("questionCalculations", groupedQuestionCalculationDtos);
		model.addAttribute("groupCalculations", groupCalculationDtos);
		model.addAttribute("startupCalculations", startupCalculationDtos);
		model.addAttribute("calculation", calculationDto);

		@SuppressWarnings("unchecked")
		LinkedList<UrlLabelMapping> mappings = (LinkedList<UrlLabelMapping>) request.getAttribute("breadCrumbs");
		mappings.getLast().setLabel(calculationDto.getName());
		mappings.getLast().setUrl("/calculation-details?calculationid="+calculationDto.getId());
		model.addAttribute("breadcrumbs", mappings);		
		return "calculation-details";
	}

	/**
	 * This method shows summaries for each calculation
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return "calculations" String
	 */
	@Link(label = "Calculations", parent = "Home", url = "/calculations")
	@RequestMapping(value = { "/calculations" }, method = RequestMethod.GET)
	public String getCalculations(HttpServletRequest request, Model model) {
		List<Calculation> calculations = calculationService.findAll();
		List<CalculationDTO> calculationDtos = calculationService.convert(calculations);
		model.addAttribute("calculations", calculationDtos);
		model.addAttribute("breadcrumbs", request.getAttribute("breadCrumbs"));
		return "calculations";
	}

	/**
	 * This method is used to check if the filters
	 * output any snapshots
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return "calculations" String
	 */
	@ResponseBody
	@RequestMapping(value = { "/checkfilter" }, method = RequestMethod.POST, consumes = "application/json")
	public String checkFilter(@RequestBody List<AjaxQuestion> questions) {
		for (AjaxQuestion q : questions) {
			if (q.getAnswerIds() == null || q.getAnswerIds().isEmpty()) {
				return "{\"result\":\"missingInput\"}";
			}
		}	
		List<Question> filterQuestions = questionService.findByFilter(true);		
		List<SnapshotLine> snapshotlines = snapshotlineService.createFilterSnapshotlines(questions, filterQuestions);		
		List<Snapshot> filteredSnapshots = snapshotService.filterSnapshots(filterQuestions, snapshotlines);		
		if (!filteredSnapshots.isEmpty()) {
			return "{\"result\":\"resultsFound\"}";
		} else {
			return "{\"result\":\"noResultsFound\"}";
		}
	}

	/**
	 * This method is used to create snapshotLines from the calculation filter
	 * 
	 * @param request
	 * @param filterQuestions
	 * @return List<SnapshotLine>
	 */
	private List<SnapshotLine> createFilterSnapshotlines(HttpServletRequest request, List<Question> filterQuestions) {	
		List<SnapshotLine> snapshotLines = new ArrayList<SnapshotLine>();	
		for (Question question : filterQuestions) {
			SnapshotLine snapshotLine = new SnapshotLine();
			snapshotLine.setQuestion(question);
			Integer id = question.getId();
			List<AnswerOption> multipleSelectedAnswers = new ArrayList<AnswerOption>();
			List<AnswerOption> answerOptions = question.getAnswergroup().getOptions();

			boolean hasSelected = false;
			for (AnswerOption answerOption : answerOptions) {
				Integer answerOptionId = answerOption.getId();
				String value = request.getParameter("multiple_" + id + "_" + answerOptionId);
				if (value != null) {
					multipleSelectedAnswers.add(answerOption);
					hasSelected = true;
				}
			}
			if (hasSelected == false) {
				break;
			}

			AnswerOption[] selectedAnswers = new AnswerOption[multipleSelectedAnswers.size()];
			multipleSelectedAnswers.toArray(selectedAnswers);
			if (multipleSelectedAnswers.isEmpty()) {
				snapshotLine.setMultipleSelectedAnswers(null);
			} else {
				snapshotLine.setMultipleSelectedAnswers(selectedAnswers);
				snapshotLines.add(snapshotLine);
			}
		}		
		return snapshotLines;
	}
	
}