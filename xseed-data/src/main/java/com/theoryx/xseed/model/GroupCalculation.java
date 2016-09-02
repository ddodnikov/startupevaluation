package com.theoryx.xseed.model;

import java.io.Serializable;

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
@Table(name = "group_calculations")
public class GroupCalculation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1825703498736295828L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Column(name = "number_of_startups", nullable = false, unique = false)
	private Integer numberOfStartups;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "calculation_id", referencedColumnName = "id")
	private Calculation calculation;

	@NotNull
	@Column(name = "formula_a", nullable = false, unique = false)
	private Double formula_a;

	@NotNull
	@Column(name = "formula_b", nullable = false, unique = false)
	private Double formula_b;

	@NotNull
	@Column(name = "formula_c", nullable = false, unique = false)
	private Double formula_c;

	@NotNull
	@Column(name = "formula_d", nullable = false, unique = false)
	private Double formula_d;

	@NotNull
	@Column(name = "formula_e", nullable = false, unique = false)
	private Double formula_e;

	@NotNull
	@Column(name = "formula_f", nullable = false, unique = false)
	private Double formula_f;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumberOfStartups() {
		return numberOfStartups;
	}

	public void setNumberOfStartups(Integer numberOfStartups) {
		this.numberOfStartups = numberOfStartups;
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

	public GroupCalculation(Integer numberOfStartups, Calculation calculation, Double formula_a, Double formula_b,
			Double formula_c, Double formula_d, Double formula_e, Double formula_f) {
		super();
		this.numberOfStartups = numberOfStartups;
		this.calculation = calculation;
		this.formula_a = formula_a;
		this.formula_b = formula_b;
		this.formula_c = formula_c;
		this.formula_d = formula_d;
		this.formula_e = formula_e;
		this.formula_f = formula_f;
	}
	
	public GroupCalculation(){
	}

}
