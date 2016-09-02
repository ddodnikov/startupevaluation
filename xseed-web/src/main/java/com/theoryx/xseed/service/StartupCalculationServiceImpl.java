package com.theoryx.xseed.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theoryx.xseed.dto.StartupCalculationDTO;
import com.theoryx.xseed.model.Calculation;
import com.theoryx.xseed.model.KPIAdjustment;
import com.theoryx.xseed.model.Snapshot;
import com.theoryx.xseed.model.SnapshotLine;
import com.theoryx.xseed.model.StartupCalculation;
import com.theoryx.xseed.repository.StartupCalculationRepository;
import com.theoryx.xseed.utils.NumberUtils;

@Service
public class StartupCalculationServiceImpl implements StartupCalculationService {

	@Autowired
	private StartupCalculationRepository startupCalculationRepository;
	@Autowired
	private KPIAdjustmentService kPIAdjustmentService;
	@Autowired
	private SnapshotlineService snapshotlineService;

	@Override
	public StartupCalculation createStartupCalculationLine(Calculation calculation, List<Snapshot> filteredSnapshots,
			Snapshot snapshot, Integer kpiQuestionId) {
		if (snapshot != null) {
			StartupCalculation startupCalculation = new StartupCalculation();
			startupCalculation.setSnapshot(snapshot);
			startupCalculation.setStartup(snapshot.getStartup());
			SnapshotLine line = snapshotlineService.findByQuestionIdAndSnapshotId(kpiQuestionId, snapshot.getId());
			KPIAdjustment kpi = kPIAdjustmentService.findByAnswerIdAndQuestionId(line.getSelected_answer().getId(), kpiQuestionId);
			startupCalculation.setKpi(kpi.getValue());
			Integer valueOfKpi = startupCalculation.getKpi();
			Integer sumOfKpi = kPIAdjustmentService.sumOfAllKpis(filteredSnapshots, kpiQuestionId);
			Integer numberOfStartups = filteredSnapshots.size();
			Double formulaA = (double) valueOfKpi / (double) sumOfKpi;
			Double formulaB = formulaA * numberOfStartups;
			Double formulaC = Math.log(formulaB);
			Double formulaD = formulaA * formulaC;
			startupCalculation.setFormula_a(formulaA);
			startupCalculation.setFormula_b(formulaB);
			startupCalculation.setFormula_c(formulaC);
			startupCalculation.setFormula_d(formulaD);
			startupCalculation.setCalculation(calculation);
			startupCalculation = startupCalculationRepository.save(startupCalculation);
			return startupCalculation;
		} else {
			return null;
		}
	}

	@Override
	public StartupCalculation findBySnapshotIdAndCalculationId(Integer snapshotId, Integer calculationId) {
		return startupCalculationRepository.findBySnapshotIdAndCalculationId(snapshotId, calculationId);
	}

	@Override
	public Double createStartupCalculations(Calculation calculation, List<Snapshot> filteredSnapshots,
			List<Snapshot> snapshots, Integer kpiQuestionId) {
		Double sumFormulaD = 0.0;
		for (Snapshot snapshot : snapshots) {
			StartupCalculation startupCalculation = createStartupCalculationLine(calculation, filteredSnapshots, snapshot, kpiQuestionId);
			sumFormulaD += startupCalculation.getFormula_d();
		}
		return sumFormulaD;
	}

	@Override
	public List<StartupCalculationDTO> convert(List<StartupCalculation> startupCalculations) {
		List<StartupCalculationDTO> dtos = new ArrayList<StartupCalculationDTO>();
		for (StartupCalculation startupCalculation : startupCalculations) {
			dtos.add(convertStartupCalculationToStartupCalculationDTO(startupCalculation));
		}
		return dtos;
	}

	@Override
	public StartupCalculationDTO convertStartupCalculationToStartupCalculationDTO(
			StartupCalculation startupCalculation) {
		if (startupCalculation != null) {
			StartupCalculationDTO dto = new StartupCalculationDTO();
			dto.setStartupName(startupCalculation.getStartup().getName());
			dto.setKpi(startupCalculation.getKpi());
			dto.setFormula_a(NumberUtils.round(startupCalculation.getFormula_a()));
			dto.setFormula_b(NumberUtils.round(startupCalculation.getFormula_b()));
			dto.setFormula_c(NumberUtils.round(startupCalculation.getFormula_c()));
			dto.setFormula_d(NumberUtils.round(startupCalculation.getFormula_d()));
			return dto;
		}
		return null;
	}
}
