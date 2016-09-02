package com.theoryx.xseed.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theoryx.xseed.dto.QuestionCalculationDTO;
import com.theoryx.xseed.model.AnswerOption;
import com.theoryx.xseed.model.Calculation;
import com.theoryx.xseed.model.Question;
import com.theoryx.xseed.model.QuestionCalculation;
import com.theoryx.xseed.model.Snapshot;
import com.theoryx.xseed.repository.QuestionCalculationRepository;
import com.theoryx.xseed.utils.NumberUtils;


@Service
public class QuestionCalculationServiceImpl implements QuestionCalculationService {

	@Autowired
	private QuestionCalculationRepository questionCalculationRepository;
	@Autowired
	private StartupCalculationService startupCalculationService;
	@Autowired
	private SnapshotlineService snapshotlineService;
	@Autowired
	private QuestionService qestionService;
	@Autowired
	private AnswerOptionService answerOptionService;

	@Override
	public Map<Double, List<QuestionCalculation>> createQuestionCalculations(Calculation calculation, List<Question> algoQuestions,
			List<Snapshot> filteredSnapshots, Double sumFormulaD) {
		Double sumFormulaE = 0.0;
		List<QuestionCalculation> questionCalculations = new ArrayList<QuestionCalculation>();
		for (Question question : algoQuestions) {
			for (AnswerOption answerOption : question.getAnswergroup().getOptions()) {
				QuestionCalculation questionCalculation = new QuestionCalculation();
				questionCalculation.setCalculation(calculation);
				questionCalculation.setQuestion(question);
				questionCalculation.setAnswerOption(answerOption);
				Double formulaA = 0.0;
				Integer numberOfStartups = 0;
				for (Snapshot snapshot : filteredSnapshots) {
					if (snapshotlineService.findByQuestionIdAndSnapshotId(question.getId(), snapshot.getId())
							.getSelected_answer().getId() == answerOption.getId()) {
						formulaA += startupCalculationService
								.findBySnapshotIdAndCalculationId(snapshot.getId(), calculation.getId()).getFormula_a();
						numberOfStartups++;
					}
				}
				Double formulaB = 0.0;
				if (formulaA != 0.0) {
					formulaB = formulaA / ((double) numberOfStartups / (double) filteredSnapshots.size());
				}
				Double formulaC = 0.0;
				if (formulaB != 0.0) {
					formulaC = Math.log(formulaB);
				}
				Double formulaD = formulaA * formulaC;
				Double formulaE = formulaD / ((double) algoQuestions.size() - 1);
				Double formulaF = 100 * (formulaE / sumFormulaD);

				questionCalculation.setNumberOfStartups(numberOfStartups);
				questionCalculation.setFormula_a(formulaA);
				questionCalculation.setFormula_b(formulaB);
				questionCalculation.setFormula_c(formulaC);
				questionCalculation.setFormula_d(formulaD);
				questionCalculation.setFormula_e(formulaE);
				questionCalculation.setFormula_f(formulaF);
				questionCalculation = questionCalculationRepository.save(questionCalculation);
				sumFormulaE += formulaE;
				questionCalculations.add(questionCalculation);
			}
		}
		Map<Double, List<QuestionCalculation>> map = new HashMap<Double, List<QuestionCalculation>>();
		map.put(sumFormulaE, questionCalculations);
		return map;
	}

	@Override
	public List<QuestionCalculation> finalCalculations(Calculation calculation, List<Snapshot> filteredSnapshots, List<QuestionCalculation> questionCalculations) {
		Double tks = calculation.getTks();
		List<QuestionCalculation> calcs = questionCalculations;
		Double average = tks / (double) calcs.size();
		for (QuestionCalculation questionCalculation : questionCalculations) {
			questionCalculation.setAverage(average);
			Double specific = average * (1 + questionCalculation.getFormula_f());
			questionCalculation.setSpecific(specific);
			questionCalculationRepository.save(questionCalculation);
		}
		return calcs;
	}

	@Override
	public List<QuestionCalculation> findByCalculationId(Integer calculationId) {
		return questionCalculationRepository.findByCalculationId(calculationId);
	}

	@Override
	public List<List<List<QuestionCalculation>>> groupByAnswerGroupAndQuestion(
			List<QuestionCalculation> questionCalculations) {
		List<List<QuestionCalculation>> groupedByQuestion = new ArrayList<List<QuestionCalculation>>();
		List<Integer> grouped = new ArrayList<Integer>();
		for (int i = 0; i < questionCalculations.size(); i++) {
			QuestionCalculation questionCalculation = questionCalculations.get(i);
			if (!grouped.contains(questionCalculation.getId())) {
				List<QuestionCalculation> list = new ArrayList<QuestionCalculation>();
				list.add(questionCalculation);
				grouped.add(questionCalculation.getId());
				for (int j = i + 1; j < questionCalculations.size(); j++) {
					QuestionCalculation questionCalculation2 = questionCalculations.get(j);
					if (questionCalculation2.getQuestion().getId() == questionCalculation.getQuestion().getId()) {
						list.add(questionCalculation2);
						grouped.add(questionCalculation2.getId());
					}
				}
				groupedByQuestion.add(list);
			}
		}

		List<List<List<QuestionCalculation>>> groupedByQuestionAndAnswerGroup = new ArrayList<List<List<QuestionCalculation>>>();
		List<Integer> grouped2 = new ArrayList<Integer>();
		for (int i = 0; i < groupedByQuestion.size(); i++) {
			List<QuestionCalculation> questionCalculation = groupedByQuestion.get(i);
			if (!grouped2.contains(i)) {
				List<List<QuestionCalculation>> list = new ArrayList<List<QuestionCalculation>>();
				list.add(questionCalculation);
				grouped2.add(i);
				for (int j = i + 1; j < groupedByQuestion.size(); j++) {
					List<QuestionCalculation> questionCalculation2 = groupedByQuestion.get(j);
					if (questionCalculation2.get(0).getAnswerOption().getGroup().getId() == questionCalculation.get(0)
							.getAnswerOption().getGroup().getId()) {
						list.add(questionCalculation2);
						grouped2.add(j);
					}
				}
				groupedByQuestionAndAnswerGroup.add(list);
			}
		}
		return groupedByQuestionAndAnswerGroup;
	}

	@Override
	public QuestionCalculationDTO convertQuestionCalculationToQuestionCalculationDTO(
			List<QuestionCalculation> questionCalculations) {
		if (questionCalculations != null) {
			QuestionCalculationDTO questionCalculationDTO = new QuestionCalculationDTO();
			questionCalculationDTO.setQuestion(
					qestionService.convertQuestionToQuestionDto(questionCalculations.get(0).getQuestion()));
			questionCalculationDTO.setAverage(NumberUtils.round(questionCalculations.get(0).getAverage()));
			List<Double> specifics = new ArrayList<Double>();
			for (QuestionCalculation questionCalculation : questionCalculations) {
				specifics.add(NumberUtils.round(questionCalculation.getSpecific()));
			}
			List<Double> formulas = new ArrayList<Double>();
			for (QuestionCalculation questionCalculation : questionCalculations) {
				formulas.add(NumberUtils.round(questionCalculation.getFormula_f()));
			}
			questionCalculationDTO.setSpecifics(specifics);
			questionCalculationDTO.setFormulas(formulas);
			questionCalculationDTO.setAnswers(answerOptionService
					.convert(questionCalculations.get(0).getQuestion().getAnswergroup().getOptions()));
			return questionCalculationDTO;
		} else {
			return null;
		}
	}

	@Override
	public List<List<QuestionCalculationDTO>> convert(List<List<List<QuestionCalculation>>> grouped) {
		if (grouped != null) {
			List<List<QuestionCalculationDTO>> groupedDtos = new ArrayList<List<QuestionCalculationDTO>>();
			for (List<List<QuestionCalculation>> list : grouped) {
				List<QuestionCalculationDTO> dto = new ArrayList<QuestionCalculationDTO>();
				for (List<QuestionCalculation> smallList : list) {
					dto.add(convertQuestionCalculationToQuestionCalculationDTO(smallList));
				}
				groupedDtos.add(dto);
			}
			return groupedDtos;
		} else {
			return null;
		}
	}

}
