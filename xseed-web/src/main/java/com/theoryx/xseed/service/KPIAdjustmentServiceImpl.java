package com.theoryx.xseed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theoryx.xseed.model.KPIAdjustment;
import com.theoryx.xseed.model.Snapshot;
import com.theoryx.xseed.model.SnapshotLine;
import com.theoryx.xseed.repository.KPIAdjustmentRepository;

@Service
public class KPIAdjustmentServiceImpl implements KPIAdjustmentService {

	@Autowired
	private KPIAdjustmentRepository kPIAdjustmentRepository;
	@Autowired
	private SnapshotlineService snapshotlineService;

	@Override
	public KPIAdjustment findByAnswerIdAndQuestionId(Integer answerId, Integer questionId) {
		return kPIAdjustmentRepository.findByAnswerIdAndQuestionId(answerId, questionId);
	}

	@Override
	public Integer sumOfAllKpis(List<Snapshot> filteredSnapshots, Integer kpiQuestionId) {
		Integer sum = 0;
		for (Snapshot snapshot : filteredSnapshots) {
			Integer kpiValue = valueOfKpi(kpiQuestionId, snapshot.getId());
			sum += kpiValue;
		}
		return sum;
	}

	@Override
	public Integer valueOfKpi(Integer kpiQuestionId, Integer snapshotId) {
		SnapshotLine line = snapshotlineService.findByQuestionIdAndSnapshotId(kpiQuestionId, snapshotId);
		KPIAdjustment kpi = kPIAdjustmentRepository.findByAnswerIdAndQuestionId(line.getSelected_answer().getId(), kpiQuestionId);
		Integer kpiValue = kpi.getValue();
		return kpiValue;
	}

}
