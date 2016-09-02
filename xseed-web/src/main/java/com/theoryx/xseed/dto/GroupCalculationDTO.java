package com.theoryx.xseed.dto;

public class GroupCalculationDTO {

	private Integer numberOfStartups;
	private Double formula_a;
	private Double formula_b;
	private Double formula_c;
	private Double formula_d;
	private Double formula_e;
	private Double formula_f;

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

	public Integer getNumberOfStartups() {
		return numberOfStartups;
	}

	public void setNumberOfStartups(Integer numberOfStartups) {
		this.numberOfStartups = numberOfStartups;
	}

	public GroupCalculationDTO(Integer numberOfStartups, Double formula_a, Double formula_b, Double formula_c,
			Double formula_d, Double formula_e, Double formula_f) {
		super();
		this.numberOfStartups = numberOfStartups;
		this.formula_a = formula_a;
		this.formula_b = formula_b;
		this.formula_c = formula_c;
		this.formula_d = formula_d;
		this.formula_e = formula_e;
		this.formula_f = formula_f;
	}

	public GroupCalculationDTO(){
	}

}
