package com.theoryx.xseed.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theoryx.xseed.dto.CalculationDTO;
import com.theoryx.xseed.model.Calculation;
import com.theoryx.xseed.model.Question;
import com.theoryx.xseed.model.QuestionCalculation;
import com.theoryx.xseed.model.Snapshot;
import com.theoryx.xseed.repository.CalculationRepository;
import com.theoryx.xseed.utils.NumberUtils;
import com.theoryx.xseed.utils.ValidationUtils;

@Service
public class CalculationServiceImpl implements CalculationService {

	@Autowired
	private CalculationRepository calculationRepository;
	@Autowired
	private GroupCalculationService groupCalculationService;
	@Autowired
	private QuestionCalculationService questionCalculationService;
	@Autowired
	private StartupCalculationService startupCalculationService;
	@Autowired
	private SnapshotService snapshotService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private UserService userService;

	@Override
	public Calculation createCalculation(Calculation calculation) {
		calculation = calculationRepository.save(calculation);
		return calculation;
	}

	@Override
	public Calculation setFinalFormulas(Calculation calculation, List<Snapshot> filteredSnapshots, 
			Double sumFormulaD, Double sumFormulaEGroup, Double sumFormulaEQuestion) {
		Double t = sumFormulaD;
		calculation.setT(100.0);
		Double tk = (sumFormulaEGroup / t) * 100;
		calculation.setTk(tk);
		Double tks = (sumFormulaEQuestion / t) * 100;
		calculation.setTks(tks);
		calculation.setTki(tk - tks);
		calculation.setTu(calculation.getT() - tk);
		return calculation;
	}

	@Override
	public Calculation findById(Integer id) {
		return calculationRepository.findOne(id);
	}

	@Override
	public CalculationDTO convertCalculationToCalculationDTO(Calculation calculation) {
		if (calculation != null) {
			CalculationDTO calculationDTO = new CalculationDTO();
			calculationDTO.setId(calculation.getId());
			calculationDTO.setName(calculation.getName());
			calculationDTO.setDate(calculation.getDate().toLocalDate());
			calculationDTO.setNumberOfStartups(calculation.getNumberOfStartups());
			calculationDTO.setT(NumberUtils.round(calculation.getT()));
			calculationDTO.setTk(NumberUtils.round(calculation.getTk()));
			calculationDTO.setTks(NumberUtils.round(calculation.getTks()));
			calculationDTO.setTki(NumberUtils.round(calculation.getTki()));
			calculationDTO.setTu(NumberUtils.round(calculation.getTu()));
			calculationDTO.setKpi(questionService.convertQuestionToQuestionDto(calculation.getKpi()));
			return calculationDTO;
		} else {
			return null;
		}
	}

	@Override
	public List<Calculation> findAll() {
		return calculationRepository.findAll();
	}

	@Override
	public List<CalculationDTO> convert(List<Calculation> calculations) {
		List<CalculationDTO> calculationDTOs = new ArrayList<CalculationDTO>();
		for (Calculation calculation : calculations) {
			calculationDTOs.add(convertCalculationToCalculationDTO(calculation));
		}
		return calculationDTOs;
	}
	
	@Transactional
	@Override
	public Calculation calculate(List<Snapshot> filteredSnapshots, Question kpiQuestion, String calculationName) {
		//Grouping snapshots based on same answers to algo questions
		List<List<Snapshot>> groups = snapshotService.groupSnapshots(filteredSnapshots);
		List<Question> algoQuestions = questionService.findByAlgo(true);
		algoQuestions = questionService.filterAlgoQuestions(filteredSnapshots, algoQuestions);

		Calculation calculation = new Calculation();
		calculation.setDate(LocalDateTime.now());
		calculation.setKpi(kpiQuestion);
		calculation.setNumberOfStartups(filteredSnapshots.size());
		calculation.setUser(userService.getCurrentUser());
		calculation.setName(ValidationUtils.validateCalculationName(calculationName));

		//Persisting the calculation
		calculation = createCalculation(calculation);

		//Creating startup calculations and returning sum of formula d for later use
		Double sumFormulaD = startupCalculationService.createStartupCalculations(calculation,
				filteredSnapshots, filteredSnapshots, kpiQuestion.getId());
		//Creating group calculations and returning sum of formula e for later use
		Double sumFormulaEGroup = groupCalculationService.createGroupCalculations(groups,
				filteredSnapshots, calculation);
		//Creating question calculations and returning sum of formula e AND the persisted question calculations for later use
		Map<Double, List<QuestionCalculation>> questionMap = questionCalculationService
				.createQuestionCalculations(calculation, algoQuestions, filteredSnapshots, sumFormulaD);
		//Getting from the map
		Double sumFormulaEQuestion = questionMap.entrySet().iterator().next().getKey();
		List<QuestionCalculation> questionCalculation = questionMap.get(sumFormulaEQuestion);

		//Calculating the final results for the calculation and persisting
		calculation = setFinalFormulas(calculation, filteredSnapshots, sumFormulaD, sumFormulaEGroup, sumFormulaEQuestion);
		//Persisting the calculation
		createCalculation(calculation);
		calculation = calculationRepository.findOne(calculation.getId());
		//Calculating the final results for the questions and persisting
		questionCalculationService.finalCalculations(calculation, filteredSnapshots, questionCalculation);
		
		return calculation;
	}

	@Override
	public Calculation getLatestCalculationByStartupID(Integer id) {
		Calculation calculation = calculationRepository.findLatestCalculation(id);		
		return calculation;
	}

}
