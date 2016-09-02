package com.theoryx.xseed.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theoryx.xseed.dto.SurveyQuestionDTO;
import com.theoryx.xseed.model.Question;
import com.theoryx.xseed.model.Survey;
import com.theoryx.xseed.model.SurveyQuestion;
import com.theoryx.xseed.repository.SurveyQuestionRepository;

@Service
public class SurveyQuestionServiceImpl implements SurveyQuestionService {

	@Autowired
	private SurveyQuestionRepository surveyQuestionRepository;
	@Autowired
	private QuestionService questionService;

	@Override
	public SurveyQuestionDTO convertSurveyQuestionToSurveyQuestionDTO(SurveyQuestion surveyQuestion) {
		SurveyQuestionDTO surveyQuestionDTO = null;
		if (surveyQuestion != null) {
			surveyQuestionDTO = new SurveyQuestionDTO();
			Integer id = surveyQuestion.getId();
			if (id != null) {
				surveyQuestionDTO.setId(id);
			}
			surveyQuestionDTO.setPageNumber(surveyQuestion.getPageNumber());
			surveyQuestionDTO.setPosition(surveyQuestion.getPosition());
			Question question = surveyQuestion.getQuestion();
			if (question != null) {
				surveyQuestionDTO.setQuestion(questionService.convertQuestionToQuestionDto(question));
			}
		}
		return surveyQuestionDTO;
	}

	@Override
	public List<SurveyQuestionDTO> getSurveyQuestionDtos(List<SurveyQuestion> surveyQuestions) {
		List<SurveyQuestionDTO> questionDtos = new ArrayList<SurveyQuestionDTO>();
		for (SurveyQuestion surveyQuestion : surveyQuestions) {
			questionDtos.add(convertSurveyQuestionToSurveyQuestionDTO(surveyQuestion));
		}
		Collections.sort(questionDtos, new Comparator<SurveyQuestionDTO>() {
			@Override
			public int compare(SurveyQuestionDTO o1, SurveyQuestionDTO o2) {
				if (o1.getPosition() == o2.getPosition())
					return 0;
				return o1.getPosition() < o2.getPosition() ? -1 : 1;
			}
		});
		return questionDtos;
	}

	@Override
	public int getNumberOfServeyQuestions(Survey survey) {
		return surveyQuestionRepository.getNumberOfSurveyQuestions(survey.getId());
	}

	@Override
	public List<List<SurveyQuestionDTO>> getSortedDtosByPage(List<SurveyQuestion> questions) {
		questions = sortByPageAndPosition(questions);
		List<SurveyQuestionDTO> sortedQuestionDtos = getSortedDtos(questions);
		List<List<SurveyQuestionDTO>> mappedByPage = new ArrayList<List<SurveyQuestionDTO>>();

		int i = 0;
		while (i < sortedQuestionDtos.size()) {
			List<SurveyQuestionDTO> mapped = new ArrayList<SurveyQuestionDTO>();
			mapped.add(sortedQuestionDtos.get(i));
			if (i < sortedQuestionDtos.size() - 1) {
				i++;
				while (sortedQuestionDtos.get(i).getPageNumber() == sortedQuestionDtos.get(i - 1).getPageNumber()) {
					mapped.add(sortedQuestionDtos.get(i));
					if (i < sortedQuestionDtos.size() - 1) {
						i++;
					} else {
						break;
					}
				}
				mappedByPage.add(mapped);
			} else {
				break;
			}
		}
		return mappedByPage;
	}

	@Override
	public List<SurveyQuestion> sortByPageAndPosition(List<SurveyQuestion> questions) {
		if (questions == null) {
			return null;
		}
		Collections.sort(questions, new Comparator<SurveyQuestion>() {
			@Override
			public int compare(SurveyQuestion o1, SurveyQuestion o2) {
				if (o1.getPageNumber() == o2.getPageNumber()) {
					if (o1.getPosition() != o2.getPosition()) {
						return o1.getPosition() < o2.getPosition() ? -1 : 1;
					} else {
						return 1;
					}
				} else {
					return o1.getPageNumber() < o2.getPageNumber() ? -1 : 1;
				}
			}
		});
		return questions;
	}

	@Override
	public List<SurveyQuestionDTO> getSortedDtos(List<SurveyQuestion> questions) {
		questions = sortByPageAndPosition(questions);
		List<SurveyQuestionDTO> sortedDtos = new ArrayList<SurveyQuestionDTO>();
		for (SurveyQuestion surveyQuestion : questions) {
			sortedDtos.add(convertSurveyQuestionToSurveyQuestionDTO(surveyQuestion));
		}
		return sortedDtos;
	}

	@Override
	public List<SurveyQuestion> getSurveyQuestionsByQuestions(List<Question> questions) {
		List<SurveyQuestion> surveyQuestions = new ArrayList<SurveyQuestion>();
		for (Question question : questions) {
			surveyQuestions.add(surveyQuestionRepository.findByQuestionId(question.getId()));
		}
		return surveyQuestions;
	}

	@Override
	public List<SurveyQuestionDTO> getSurveyQuestionDtosByQuestions(List<Question> questions) {
		List<SurveyQuestion> surveyQuestions = getSurveyQuestionsByQuestions(questions);
		List<SurveyQuestionDTO> questionDtos = new ArrayList<SurveyQuestionDTO>();
		for (SurveyQuestion surveyQuestion : surveyQuestions) {
			questionDtos.add(convertSurveyQuestionToSurveyQuestionDTO(surveyQuestion));
		}
		return questionDtos;
	}

}
