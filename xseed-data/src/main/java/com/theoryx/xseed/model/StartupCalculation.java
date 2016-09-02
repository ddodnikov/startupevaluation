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
@Table(name = "startup_calculations")
public class StartupCalculation {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "startup_id", referencedColumnName = "id")
	private Startup startup;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "snapshot_id", referencedColumnName = "id")
	private Snapshot snapshot;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "calculation_id", referencedColumnName = "id")
	private Calculation calculation;

	@NotNull
	@Column(name = "kpi")
	private Integer kpi;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Startup getStartup() {
		return startup;
	}

	public void setStartup(Startup startup) {
		this.startup = startup;
	}

	public Snapshot getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(Snapshot snapshot) {
		this.snapshot = snapshot;
	}

	public Integer getKpi() {
		return kpi;
	}

	public void setKpi(Integer kpi) {
		this.kpi = kpi;
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

	public Calculation getCalculation() {
		return calculation;
	}

	public void setCalculation(Calculation calculation) {
		this.calculation = calculation;
	}

}
