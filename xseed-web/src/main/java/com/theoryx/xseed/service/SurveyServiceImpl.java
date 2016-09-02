package com.theoryx.xseed.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theoryx.xseed.dto.SurveyDTO;
import com.theoryx.xseed.model.Survey;
import com.theoryx.xseed.repository.SurveyRepository;

@Service
public class SurveyServiceImpl implements SurveyService {

	@Autowired
	private SurveyRepository surveyRepository;
	@Autowired
	private SurveyQuestionService surveyQuestionService;

	@Override
	public Survey getById(Integer surveyId) {
		return surveyRepository.findOne(surveyId);
	}

	@Override
	public List<Survey> getAllSurveys() {
		return (List<Survey>) surveyRepository.findAll();
	}

	@Override
	public SurveyDTO convertSurveyToSurveyDTO(Survey survey) {
		SurveyDTO surveyDto = null;
		if (survey != null) {
			surveyDto = new SurveyDTO();
			surveyDto.setId(survey.getId());
			surveyDto.setNumberOfQuestions(surveyQuestionService.getNumberOfServeyQuestions(survey));
			String name = survey.getName();
			if (name != null) {
				surveyDto.setName(name);
			}
		}
		return surveyDto;
	}

	@Override
	public List<SurveyDTO> getAllSurveyDtos() {
		List<Survey> surveys = getAllSurveys();
		List<SurveyDTO> surveyDtos = null;
		if (surveys != null) {
			surveyDtos = new ArrayList<SurveyDTO>();
			for (Survey survey : surveys) {
				surveyDtos.add(convertSurveyToSurveyDTO(survey));
			}
		}
		return surveyDtos;
	}

}
