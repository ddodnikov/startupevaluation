package com.theoryx.xseed.service;

import java.util.List;

import com.theoryx.xseed.dto.SurveyDTO;
import com.theoryx.xseed.model.Survey;

public interface SurveyService {

	/**
	 * Returns all Survey models
	 * 
	 * @return List<Survey>
	 */
	List<Survey> getAllSurveys();

	/**
	 * Convert Survey model to SurveyDTO
	 * 
	 * @param survey
	 * @return SurveyDTO
	 */
	SurveyDTO convertSurveyToSurveyDTO(Survey survey);

	/**
	 * Returns all Survey models as SurveyDTO
	 * 
	 * @return List<SurvetDTO>
	 */
	List<SurveyDTO> getAllSurveyDtos();

	/**
	 * Finds a Survey model by its id
	 * 
	 * @param surveyId
	 * @return Survey
	 */
	Survey getById(Integer surveyId);

}
