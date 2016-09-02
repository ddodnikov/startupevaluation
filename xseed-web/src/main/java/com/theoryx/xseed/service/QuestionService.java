package com.theoryx.xseed.service;

import java.util.List;

import com.theoryx.xseed.dto.QuestionDTO;
import com.theoryx.xseed.model.Question;
import com.theoryx.xseed.model.Snapshot;

public interface QuestionService {

	/**
	 * Converts Question model to QuestionDTO
	 * 
	 * @param question
	 * @return QuestionDTO
	 */
	QuestionDTO convertQuestionToQuestionDto(Question question);

	/**
	 * Converts QuestionDTO to Question model
	 * 
	 * @param question
	 * @return Question model
	 */
	Question convertQuestionDTOToQuestion(QuestionDTO question);

	/**
	 * Finds a Question by id
	 * 
	 * @param questionId
	 * @return Question model
	 */
	Question findbyId(Integer questionId);

	/**
	 * Finds all filter questions
	 * 
	 * @param filter
	 * @return List<Question>
	 */
	List<Question> findByFilter(boolean filter);

	/**
	 * Finds all kpi questions
	 * 
	 * @param kpi
	 * @return List<Question>
	 */
	List<Question> findByKpi(boolean kpi);

	/**
	 * Finds all algo questions
	 * 
	 * @param algo
	 * @return List<Question>
	 */
	List<Question> findByAlgo(boolean algo);

	/**
	 * Converts list of Question models to list of QuestionDTOs
	 * 
	 * @param filterQuestions
	 * @return List<QuestionDTO>
	 */
	List<QuestionDTO> convert(List<Question> filterQuestions);

	List<Question> findAll();

	Question save(Question question);

	List<Question> filterAlgoQuestions(List<Snapshot> filteredSnapshots, List<Question> algoQuestions);

}
