package com.theoryx.xseed.dto;

public class StartupCalculationDTO {

	private String startupName;
	private Integer kpi;
	private Double formula_a;
	private Double formula_b;
	private Double formula_c;
	private Double formula_d;

	public String getStartupName() {
		return startupName;
	}

	public void setStartupName(String startupName) {
		this.startupName = startupName;
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

	public Integer getKpi() {
		return kpi;
	}

	public void setKpi(Integer kpi) {
		this.kpi = kpi;
	}

}
