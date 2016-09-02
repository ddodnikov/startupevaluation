package com.theoryx.xseed.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theoryx.xseed.dto.ReportAnswer;
import com.theoryx.xseed.dto.ReportRow;
import com.theoryx.xseed.model.AnswerOption;
import com.theoryx.xseed.model.Calculation;
import com.theoryx.xseed.model.GroupCategory;
import com.theoryx.xseed.model.Question;
import com.theoryx.xseed.model.QuestionCalculation;
import com.theoryx.xseed.model.Snapshot;
import com.theoryx.xseed.model.SnapshotLine;
import com.theoryx.xseed.utils.NumberUtils;

@Service
public class ReportsServiceImpl implements ReportsService {

	@Autowired
	private SnapshotService snaphsotService;
	@Autowired
	private CalculationService calculationService;

	@Override
	public LinkedHashMap<String, LinkedHashMap<String, LinkedList<ReportRow>>> getReportRows(Integer startUpID) {
		LinkedHashMap<String, LinkedHashMap<String, LinkedList<ReportRow>>> phaseGroupQuestion = new LinkedHashMap<String, LinkedHashMap<String, LinkedList<ReportRow>>>();

		List<Snapshot> snapshots = snaphsotService.findAllSnapshotsByStartupId(startUpID);
		Calculation latestCalculation = calculationService.getLatestCalculationByStartupID(startUpID);
		if (snapshots != null && latestCalculation != null) {
			// Prepare data from snapshots
			Map<Snapshot, HashMap<Question, AnswerOption>> snapshotQuestionAnswer = prepareDataFromSnapshot(snapshots);
			// Prepare data from calculations
			List<QuestionCalculation> questionCalculations = latestCalculation.getQuestionCalculations();
			Map<Question, HashMap<AnswerOption, Double>> calculationQuestionAnswer = prepareDataFromCalculations(
					questionCalculations);
			// iterate through all questions
			for (Question question : calculationQuestionAnswer.keySet()) {
				// create a row of data for each question
				ReportRow row = new ReportRow();
				row.setQuestionTitle(question.getText());
				LinkedList<ReportAnswer> answerValues = new LinkedList<ReportAnswer>();

				// calculate the target value
				Double minValue = 0.0;
				boolean minInit = false;
				String targetValue = "";

				// for (Snapshot snapshot : snapshotQuestionAnswer.keySet()) {
				for (Iterator<Snapshot> it = snapshotQuestionAnswer.keySet().iterator(); it.hasNext();) {
					Snapshot snapshot = it.next();
					AnswerOption answer = snapshotQuestionAnswer.get(snapshot).get(question);
					// if a calculation question is missing in the snapshot
					if (answer == null) {
						// remove the snapshot
						it.remove();
						continue;
					}
					if (!minInit) {
						minValue = calculationQuestionAnswer.get(question).get(answer);
						minInit = true;
					}
					Double value = calculationQuestionAnswer.get(question).get(answer);
					answerValues.add(new ReportAnswer(answer.getText(), NumberUtils.round(value)));
					
					if (minValue >= value) {
						targetValue = answer.getText();
						minValue = value;
					}
				}

				row.setAnswers(answerValues);
				row.setTargetValue(targetValue);

				// calculate delta
				boolean delta = answerValues.get(0).getAnswer().equals(targetValue);
				row.setDelta(delta);

				// add the row to corresponding group categories and phase
				// category
				for (GroupCategory groupCategory : question.getGroupCategories()) {
					LinkedHashMap<String, LinkedList<ReportRow>> groupCategoryQuestion = new LinkedHashMap<String, LinkedList<ReportRow>>();
					if (phaseGroupQuestion.containsKey(groupCategory.getPhaseCategory().getName())) {
						groupCategoryQuestion = phaseGroupQuestion.get(groupCategory.getPhaseCategory().getName());
					}

					LinkedList<ReportRow> rows = new LinkedList<ReportRow>();
					if (groupCategoryQuestion.containsKey(groupCategory.getName())) {
						rows = groupCategoryQuestion.get(groupCategory.getName());
					}

					rows.add(row);
					groupCategoryQuestion.put(groupCategory.getName(), rows);
					phaseGroupQuestion.put(groupCategory.getPhaseCategory().getName(), groupCategoryQuestion);
				}
			}
			return phaseGroupQuestion;
		} else {
			return null;
		}
	}

	private Map<Snapshot, HashMap<Question, AnswerOption>> prepareDataFromSnapshot(List<Snapshot> snapshots) {
		Map<Snapshot, HashMap<Question, AnswerOption>> questionAndValues = new LinkedHashMap<Snapshot, HashMap<Question, AnswerOption>>();
		for (Snapshot snapshot : snapshots) {
			HashMap<Question, AnswerOption> questionAnswers = new HashMap<Question, AnswerOption>();
			for (SnapshotLine snapshotLine : snapshot.getSnapshotlines()) {
				questionAnswers.put(snapshotLine.getQuestion(), snapshotLine.getSelected_answer());
			}
			questionAndValues.put(snapshot, questionAnswers);
		}
		return questionAndValues;
	}

	private Map<Question, HashMap<AnswerOption, Double>> prepareDataFromCalculations(
			List<QuestionCalculation> questionCalculations) {
		Map<Question, HashMap<AnswerOption, Double>> questionAndValues = new LinkedHashMap<Question, HashMap<AnswerOption, Double>>();
		for (QuestionCalculation questionCalc : questionCalculations) {
			if (questionAndValues.containsKey(questionCalc.getQuestion())) {
				HashMap<AnswerOption, Double> answerValue = questionAndValues.get(questionCalc.getQuestion());
				answerValue.put(questionCalc.getAnswerOption(), questionCalc.getFormula_f());
				questionAndValues.put(questionCalc.getQuestion(), answerValue);
			} else {
				HashMap<AnswerOption, Double> answerValue = new HashMap<AnswerOption, Double>();
				answerValue.put(questionCalc.getAnswerOption(), questionCalc.getFormula_f());
				questionAndValues.put(questionCalc.getQuestion(), answerValue);
			}
		}
		return questionAndValues;
	}
}
