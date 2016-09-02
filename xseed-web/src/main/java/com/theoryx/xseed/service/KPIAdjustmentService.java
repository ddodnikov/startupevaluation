package com.theoryx.xseed.service;

import java.util.List;

import com.theoryx.xseed.model.KPIAdjustment;
import com.theoryx.xseed.model.Snapshot;

public interface KPIAdjustmentService {

	/**
	 * This method returns the value of kpi for snapshot and kpi question
	 * 
	 * @param kpiQuestionId
	 * @param snapshotId
	 * @return Integer
	 */
	Integer valueOfKpi(Integer kpiQuestionId, Integer snapshotId);
	
	
	/**
	 * This method returns the sum of all kpis in the list of snapshots for a kpi question
	 * 
	 * @param filteredSnapshots
	 * @param kpiQuestionId
	 * @return Integer
	 */
	Integer sumOfAllKpis(List<Snapshot> filteredSnapshots, Integer kpiQuestionId);
	
	
	/**
	 * This method returns a kpi based on question and answer ids
	 * 
	 * @param answerId
	 * @param questionId
	 * @return KPIAdjustment
	 */
	KPIAdjustment findByAnswerIdAndQuestionId(Integer answerId, Integer questionId);

}
