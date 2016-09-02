package com.theoryx.xseed.service;

import java.util.List;
import java.util.Set;

import com.theoryx.xseed.dto.AnswerOptionDTO;
import com.theoryx.xseed.model.AnswerOption;
import com.theoryx.xseed.model.Question;

public interface AnswerOptionService {

	/**
	 * Get list of AnswerOptionDTO converted from list of AnswerOption model
	 * 
	 * @param question
	 * @see getAnswerOption(Question question)
	 * @return List<AnswerOptionDTO>
	 */
	List<AnswerOptionDTO> getAnswerOptionDtos(Question question);

	/**
	 * Gets all AnswerOption from the database that have the same answer group
	 * as the question
	 * 
	 * @param question
	 * @return List<AnswerOptionDTO>
	 */
	List<AnswerOption> getAnswerOptions(Question question);

	/**
	 * Converts AnswerOption model to AnswerOptionDTO
	 * 
	 * @param answerOption
	 * @return AnswerOptionDTO
	 */
	AnswerOptionDTO convertAnswerOptionToAnswerOptionDto(AnswerOption answerOption);

	/**
	 * Converts List<AnswerOption> models to List<AnswerOptionDTO>
	 * 
	 * @param answerOptions
	 * @return List<AnswerOptionDTO>
	 */
	List<AnswerOptionDTO> convert(List<AnswerOption> answerOptions);

	/**
	 * Gets an AnswerOption model by the text of the answer.
	 * 
	 * @param upperCase
	 *            the text of the answer
	 * @return AnswerOption
	 */
	AnswerOption findByText(String upperCase);

	/**
	 * Gets an AnswerOption model by id
	 * 
	 * @param object
	 * @return AnswerOption
	 */
	AnswerOption findById(Integer object);

	/**
	 * Returns the id of answerOptions with text "Other"
	 * 
	 * @return Set<Integer>
	 */
	Set<Integer> getAnswerOptionOtherId();

	/**
	 * This method checks if the id is for an answer option "Other"
	 * 
	 * @param id String
	 * @return Boolean. True-if the id is for an answer option "Other",
	 * otherwise-False
	 */
	boolean isOtherChecked(String id);
}
