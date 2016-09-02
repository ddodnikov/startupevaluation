package com.theoryx.xseed.service;

import java.util.List;
import java.util.Map;

import com.theoryx.xseed.dto.QuestionCalculationDTO;
import com.theoryx.xseed.model.Calculation;
import com.theoryx.xseed.model.Question;
import com.theoryx.xseed.model.QuestionCalculation;
import com.theoryx.xseed.model.Snapshot;

public interface QuestionCalculationService {
	
	/**
	 * This method saves question calculations of a calculation in the database
	 * and returns sum of formula E for next calculation
	 * 
	 * @param calculation
	 * @param algoQuestions
	 * @param filteredSnapshots
	 * @return Double
	 */
	Map<Double, List<QuestionCalculation>> createQuestionCalculations(Calculation calculation, List<Question> algoQuestions, List<Snapshot> filteredSnapshots, Double sumFormulaD);	
	
	
	/**
	 * This method makes final calculations
	 * 
	 * @param calculation
	 * @param questionCalculation 
	 * @return List<QuestionCalculation>
	 */
	List<QuestionCalculation> finalCalculations(Calculation calculation, List<Snapshot> filteredSnapshots, List<QuestionCalculation> questionCalculation);
	
	
	/**
	 * This method returns a list of QuestionCalculations by calculation id
	 * 
	 * @param calculationId
	 * @return List<QuestionCalculation>
	 */
	List<QuestionCalculation> findByCalculationId(Integer calculationId);
	

	/**
	 * This method converts a list of QuestionCalculations to a QuestionCalculationDTO
	 * 
	 * @param questionCalculations
	 * @return QuestionCalculationDTO
	 */
	QuestionCalculationDTO convertQuestionCalculationToQuestionCalculationDTO(List<QuestionCalculation> questionCalculations);
	
	
	/**
	 * This method groups question calculations by question , then by answer group
	 * 
	 * @param questionCalculations
	 * @return List<List<List<QuestionCalculation>>>
	 */
	List<List<List<QuestionCalculation>>> groupByAnswerGroupAndQuestion(List<QuestionCalculation> questionCalculations);
	
	
	/**
	 * This method converts a list of question calculations grouped by question and by answer group
	 * into a list of QuestionCalculationDTO grouped by answer group
	 * 
	 * @param grouped
	 * @return List<List<QuestionCalculationDTO>>
	 */
	List<List<QuestionCalculationDTO>> convert(List<List<List<QuestionCalculation>>> grouped);

}
