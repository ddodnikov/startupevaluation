package com.theoryx.xseed.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "question_calculations")
public class QuestionCalculation {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id", referencedColumnName = "id")
	private Question question;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "calculation_id", referencedColumnName = "id")
	private Calculation calculation;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "answer_option_id", referencedColumnName = "id")
	private AnswerOption answerOption;

	@NotNull
	@Column(name = "formula_a")
	private Double formula_a;

	@NotNull
	@Column(name = "formula_b")
	private Double formula_b;

	@NotNull
	@Column(name = "formula_c")
	private Double formula_c;

	@NotNull
	@Column(name = "formula_d")
	private Double formula_d;

	@NotNull
	@Column(name = "formula_e")
	private Double formula_e;

	@NotNull
	@Column(name = "formula_f")
	private Double formula_f;

	@NotNull
	@Column(name = "number_of_startups")
	private Integer numberOfStartups;

	@Column(name = "average")
	private Double average;

	@Column(name = "specific")
	private Double specific;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Calculation getCalculation() {
		return calculation;
	}

	public void setCalculation(Calculation calculation) {
		this.calculation = calculation;
	}

	public Double getFormula_a() {
		return formula_a;
	}

	public void setFormula_a(Double formula_a) {
		this.formula_a = formula_a;
	}

	public Double getFormula_b() {
		return formula_b;
	}

	public void setFormula_b(Double formula_b) {
		this.formula_b = formula_b;
	}

	public Double getFormula_c() {
		return formula_c;
	}

	public void setFormula_c(Double formula_c) {
		this.formula_c = formula_c;
	}

	public Double getFormula_d() {
		return formula_d;
	}

	public void setFormula_d(Double formula_d) {
		this.formula_d = formula_d;
	}

	public Double getFormula_e() {
		return formula_e;
	}

	public void setFormula_e(Double formula_e) {
		this.formula_e = formula_e;
	}

	public Double getFormula_f() {
		return formula_f;
	}

	public void setFormula_f(Double formula_f) {
		this.formula_f = formula_f;
	}

	public AnswerOption getAnswerOption() {
		return answerOption;
	}

	public void setAnswerOption(AnswerOption answerOption) {
		this.answerOption = answerOption;
	}

	public Integer getNumberOfStartups() {
		return numberOfStartups;
	}

	public void setNumberOfStartups(Integer numberOfStartups) {
		this.numberOfStartups = numberOfStartups;
	}

	public Double getAverage() {
		return average;
	}

	public void setAverage(Double average) {
		this.average = average;
	}

	public Double getSpecific() {
		return specific;
	}

	public void setSpecific(Double specific) {
		this.specific = specific;
	}

}
