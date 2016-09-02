package com.theoryx.xseed.service;

import java.util.List;

import com.theoryx.xseed.dto.GroupCalculationDTO;
import com.theoryx.xseed.model.Calculation;
import com.theoryx.xseed.model.GroupCalculation;
import com.theoryx.xseed.model.Snapshot;

public interface GroupCalculationService {

	/**
	 * This method saves a GroupCalculation in the database
	 * 
	 * @param calculation
	 * @param group
	 * @return GroupCalculation
	 */
	GroupCalculation createGroupCalculationLine(List<Snapshot> group, List<Snapshot> filteredSnapshots, Calculation calculation);
	
	
	/**
	 * This method saves all the GroupCalculations of a calculation in the database
	 * and return sum of formula E for next calculations
	 * 
	 * @param calculation
	 * @param groupedSnapshots
	 * @return Double
	 */
	Double createGroupCalculations(List<List<Snapshot>> groupedSnapshots, List<Snapshot> filteredSnapshots, Calculation calculation);	
	
	
	/**
	 * This method finds a GroupCalculation by calculation id
	 * 
	 * @param calculationId
	 * @return List<GroupCalculation>
	 */
	List<GroupCalculation> getByCalculationId(Integer calculationId);
	
	
	/**
	 * This method converts a list of GroupCalculations into a list of GroupCalculationDTOs
	 * 
	 * @param groupCalculations
	 * @return List<GroupCalculationDTO>
	 */
	List<GroupCalculationDTO> convert(List<GroupCalculation> groupCalculations);
	
	
	/**
	 * This method converts a GroupCalculation into a GroupCalculationDTO
	 * 
	 * @param groupCalculation
	 * @return GroupCalculationDTO
	 */
	GroupCalculationDTO convertGroupCalculationToGroupCalculationDTO(GroupCalculation groupCalculation);

}
