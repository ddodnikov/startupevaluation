package com.theoryx.xseed.dto;

import java.time.LocalDate;

public class CalculationDTO {

	private Integer id;
	private QuestionDTO kpi;
	private String name;
	private LocalDate date;
	private Integer numberOfStartups;
	private Double t;
	private Double tk;
	private Double tks;
	private Double tki;
	private Double tu;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public QuestionDTO getKpi() {
		return kpi;
	}

	public void setKpi(QuestionDTO kpi) {
		this.kpi = kpi;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getNumberOfStartups() {
		return numberOfStartups;
	}

	public void setNumberOfStartups(Integer numberOfStartups) {
		this.numberOfStartups = numberOfStartups;
	}

	public Double getT() {
		return t;
	}

	public void setT(Double t) {
		this.t = t;
	}

	public Double getTk() {
		return tk;
	}

	public void setTk(Double tk) {
		this.tk = tk;
	}

	public Double getTks() {
		return tks;
	}

	public void setTks(Double tks) {
		this.tks = tks;
	}

	public Double getTki() {
		return tki;
	}

	public void setTki(Double tki) {
		this.tki = tki;
	}

	public Double getTu() {
		return tu;
	}

	public void setTu(Double tu) {
		this.tu = tu;
	}

}
