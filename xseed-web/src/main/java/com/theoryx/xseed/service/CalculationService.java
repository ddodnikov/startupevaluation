package com.theoryx.xseed.service;


import java.util.List;

import com.theoryx.xseed.dto.CalculationDTO;
import com.theoryx.xseed.model.Calculation;
import com.theoryx.xseed.model.Question;
import com.theoryx.xseed.model.Snapshot;

public interface CalculationService {
	
	/**
	 * This method saves a calculation in the database
	 * 
	 * @param calculation
	 * @return Calculation
	 */
	Calculation createCalculation(Calculation calculation);
	
	
	/**
	 * This method sets the final results of a calculation
	 * 
	 * @param calculation
	 * @return Calculation
	 */
	Calculation setFinalFormulas(Calculation calculation, List<Snapshot> filteredSnapshots, Double sumFormulaD, Double sumFormulaEGroup, Double sumFormulaEQuestion);
	
	
	/**
	 * This method finds a calculation by id
	 * 
	 * @param id
	 * @return Calculation
	 */
	Calculation findById(Integer id);
	
	
	/**
	 * This method converts a Calculation into a CalculationDTO
	 * 
	 * @param calculation
	 * @return CalculationDTO
	 */
	CalculationDTO convertCalculationToCalculationDTO(Calculation calculation);
	
	
	/**
	 * This method returns all the calculations
	 * 
	 * @return List<Calculation>
	 */
	List<Calculation> findAll();
	
	
	/**
	 * This method converts a list of Calculations into a list of CalculationDTOs
	 * 
	 * @param calculations
	 * @return List<CalculationDTO>
	 */
	List<CalculationDTO> convert(List<Calculation> calculations);
	
	
	/**
	 * This method makes all the calculations and writes to database tables
	 * 
	 * @param filteredSnapshots
	 * @param kpiQuestion
	 * @param calculationName
	 * @return Calculation
	 */
	Calculation calculate(List<Snapshot> filteredSnapshots, Question kpiQuestion, String calculationName);

	Calculation getLatestCalculationByStartupID(Integer id);
}
