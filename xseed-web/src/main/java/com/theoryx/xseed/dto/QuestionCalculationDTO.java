package com.theoryx.xseed.dto;

import java.util.List;

public class QuestionCalculationDTO {

	private QuestionDTO question;
	private Double average;
	private List<AnswerOptionDTO> answers;
	private List<Double> formulas;
	private List<Double> specifics;

	public QuestionDTO getQuestion() {
		return question;
	}

	public void setQuestion(QuestionDTO question) {
		this.question = question;
	}

	public Double getAverage() {
		return average;
	}

	public void setAverage(Double average) {
		this.average = average;
	}

	public List<AnswerOptionDTO> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerOptionDTO> answers) {
		this.answers = answers;
	}

	public List<Double> getFormulas() {
		return formulas;
	}

	public void setFormulas(List<Double> formulas) {
		this.formulas = formulas;
	}

	public List<Double> getSpecifics() {
		return specifics;
	}

	public void setSpecifics(List<Double> specifics) {
		this.specifics = specifics;
	}

}
