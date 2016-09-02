package com.theoryx.xseed.service;

import java.util.List;

import com.theoryx.xseed.dto.StartupCalculationDTO;
import com.theoryx.xseed.model.Calculation;
import com.theoryx.xseed.model.Snapshot;
import com.theoryx.xseed.model.StartupCalculation;

public interface StartupCalculationService {
	
	/**
	 * This method saves all the StartupCalculation of a calculation in the database and returns sum of formula D for the next calculation
	 * 
	 * @param calculation
	 * @param kpiQuestionId
	 * @param snapshots
	 * @return Double
	 */
	Double createStartupCalculations(Calculation calculation, List<Snapshot> snapshots, List<Snapshot> filteredSnapshots, Integer kpiQuestionId);
	
	
	/**
	 * This method saves a StartupCalculation in the database
	 * 
	 * @param calculation
	 * @param kpiQuestionId
	 * @param snapshot
	 * @return StartupCalculation
	 */
	StartupCalculation createStartupCalculationLine(Calculation calculation, List<Snapshot> filteredSnapshots, Snapshot snapshot, Integer kpiQuestionId);
	
	
	/**
	 * This method finds a StartupCalculation by snapshot id and calculation id
	 * 
	 * @param snapshotId
	 * @param calculationId
	 * @return StartupCalculation
	 */
	StartupCalculation findBySnapshotIdAndCalculationId(Integer snapshotId, Integer calculationId);

	
	/**
	 * This method converts a list of StartupCalculations into a list of StartupCalculationDTOs
	 * 
	 * @param startupCalculations
	 * @return List<StartupCalculationDTO>
	 */
	List<StartupCalculationDTO> convert(List<StartupCalculation> startupCalculations);
	
	
	/**
	 * This method converts a StartupCalculation into a tartupCalculationDTO
	 * 
	 * @param startupCalculation
	 * @return StartupCalculationDTO
	 */
	StartupCalculationDTO convertStartupCalculationToStartupCalculationDTO(StartupCalculation startupCalculation);

}
