package com.theoryx.xseed.service;

import java.util.List;

import com.theoryx.xseed.dto.SurveyQuestionDTO;
import com.theoryx.xseed.model.Question;
import com.theoryx.xseed.model.Survey;
import com.theoryx.xseed.model.SurveyQuestion;

public interface SurveyQuestionService {

	/**
	 * Converts SurveyQuestion model to SurveyQuestionDTO
	 * 
	 * @param SurveyQuestion
	 * @return SurveyQuestionDTO
	 */
	SurveyQuestionDTO convertSurveyQuestionToSurveyQuestionDTO(SurveyQuestion question);

	/**
	 * Converts List<SurvetQuestion> to List<SurveyQuestionDTO>
	 * 
	 * @param surveyQuestions
	 *            List<SurveyQuestion>
	 * @return List<SurveyQuestionDTO>
	 */
	List<SurveyQuestionDTO> getSurveyQuestionDtos(List<SurveyQuestion> surveyQuestions);

	/**
	 * Gets the number of survey question for a survey
	 * 
	 * @param survey
	 *            Survey
	 * @return int
	 */
	int getNumberOfServeyQuestions(Survey survey);

	/**
	 * Sorts SurveyQuestions by their position.
	 * 
	 * @param questions
	 *            List<SurveyQuestion>
	 * @return List<List<SurveyQuestionDTO>
	 */
	List<List<SurveyQuestionDTO>> getSortedDtosByPage(List<SurveyQuestion> questions);

	/**
	 * Sorts SurveyQuestion by page and position
	 * 
	 * @param questions
	 *            List<SurveyQuestion>
	 * @return List<SurveyQuestion>
	 */
	List<SurveyQuestion> sortByPageAndPosition(List<SurveyQuestion> questions);

	/**
	 * Convert sorted List<SurveyQuestion> to List<SurveyQuestionDTO>
	 * 
	 * @param questions
	 *            List<SurveyQuestion>
	 * @return List<SurveyQuestionDTO>
	 */
	List<SurveyQuestionDTO> getSortedDtos(List<SurveyQuestion> questions);

	/**
	 * Finds SurveyQuestion by question's id.
	 * 
	 * @param question
	 *            List<Question>
	 * @return List<SurveyQuestion>
	 */
	List<SurveyQuestion> getSurveyQuestionsByQuestions(List<Question> questions);

	/**
	 * Converts List<SurveyQuestion> to List<SurveyQuestionDTO>
	 * 
	 * @param questions
	 *            List<Question>
	 * @return List<SurveyQuestionDTO>
	 */
	List<SurveyQuestionDTO> getSurveyQuestionDtosByQuestions(List<Question> questions);

}
