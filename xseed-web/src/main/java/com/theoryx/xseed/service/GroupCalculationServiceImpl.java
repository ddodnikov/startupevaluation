package com.theoryx.xseed.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theoryx.xseed.dto.GroupCalculationDTO;
import com.theoryx.xseed.model.Calculation;
import com.theoryx.xseed.model.GroupCalculation;
import com.theoryx.xseed.model.Snapshot;
import com.theoryx.xseed.repository.GroupCalculationRepository;
import com.theoryx.xseed.utils.NumberUtils;

@Service
public class GroupCalculationServiceImpl implements GroupCalculationService {

	@Autowired
	private GroupCalculationRepository groupCalculationRepository;
	@Autowired
	private StartupCalculationService startupCalculationService;

	@Override
	public GroupCalculation createGroupCalculationLine(List<Snapshot> group, List<Snapshot> filteredSnapshots,
			Calculation calculation) {
		Double formulaA = 0.0;
		for (Snapshot snapshot : group) {
			formulaA += startupCalculationService
					.findBySnapshotIdAndCalculationId(snapshot.getId(), calculation.getId()).getFormula_a();
		}
		Double formulaB = (double) group.size() / (double) filteredSnapshots.size();
		Double formulaC = formulaA / formulaB;
		Double formulaD = 0.0;
		if (formulaC != 0.0) {
			formulaD = Math.log(formulaC);
		}
		Double formulaE = formulaA * formulaD;
		Double formulaF = formulaE / (double) filteredSnapshots.size();

		GroupCalculation groupCalculation = new GroupCalculation(group.size(), calculation, formulaA, formulaB,
				formulaC, formulaD, formulaE, formulaF);
		groupCalculation = groupCalculationRepository.save(groupCalculation);
		return groupCalculation;
	}

	@Override
	public Double createGroupCalculations(List<List<Snapshot>> groupedSnapshots,
			List<Snapshot> filteredSnapshots, Calculation calculation) {
		Double sumFormulaE = 0.0;
		for (List<Snapshot> group : groupedSnapshots) {
			GroupCalculation groupCalculation = createGroupCalculationLine(group, filteredSnapshots, calculation);
			sumFormulaE += groupCalculation.getFormula_e();
		}
		return sumFormulaE;
	}

	@Override
	public List<GroupCalculation> getByCalculationId(Integer calculationId) {
		return groupCalculationRepository.findByCalculationId(calculationId);
	}

	@Override
	public List<GroupCalculationDTO> convert(List<GroupCalculation> groupCalculations) {
		List<GroupCalculationDTO> dtos = new ArrayList<GroupCalculationDTO>();
		for (GroupCalculation groupCalculation : groupCalculations) {
			dtos.add(convertGroupCalculationToGroupCalculationDTO(groupCalculation));
		}
		return dtos;
	}

	@Override
	public GroupCalculationDTO convertGroupCalculationToGroupCalculationDTO(GroupCalculation groupCalculation) {
		if(groupCalculation != null) {
			GroupCalculationDTO dto = new GroupCalculationDTO();
			dto.setFormula_a(NumberUtils.round(groupCalculation.getFormula_a()));
			dto.setFormula_b(NumberUtils.round(groupCalculation.getFormula_b()));
			dto.setFormula_c(NumberUtils.round(groupCalculation.getFormula_c()));
			dto.setFormula_d(NumberUtils.round(groupCalculation.getFormula_d()));
			dto.setFormula_e(NumberUtils.round(groupCalculation.getFormula_e()));
			dto.setFormula_f(NumberUtils.round(groupCalculation.getFormula_f()));
			dto.setNumberOfStartups(groupCalculation.getNumberOfStartups());
			return dto;
		}
		return null;
	}
}
