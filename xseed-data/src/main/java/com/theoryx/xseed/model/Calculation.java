package com.theoryx.xseed.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "calculations")
public class Calculation {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToMany(mappedBy = "calculation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<GroupCalculation> groupCalculations;

	@OneToMany(mappedBy = "calculation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<QuestionCalculation> questionCalculations;

	@OneToMany(mappedBy = "calculation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<StartupCalculation> startupCalculations;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id", referencedColumnName = "id")
	private Question kpi;

	@NotNull
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "date", nullable = false)
	private LocalDateTime date;

	@Column(name = "number_of_startups", nullable = false)
	private Integer numberOfStartups;

	@Column(name = "T", nullable = true)
	private Double t;

	@Column(name = "Tknown", nullable = true)
	private Double tk;

	@Column(name = "Tknown_straight", nullable = true)
	private Double tks;

	@Column(name = "Tknown_interaction", nullable = true)
	private Double tki;

	@Column(name = "Tunknown", nullable = true)
	private Double tu;

	public Integer getId() {
		return id;
	}

	public List<QuestionCalculation> getQuestionCalculations() {
		return questionCalculations;
	}

	public void setQuestionCalculations(List<QuestionCalculation> questionCalculations) {
		this.questionCalculations = questionCalculations;
	}

	public List<StartupCalculation> getStartupCalculations() {
		return startupCalculations;
	}

	public void setStartupCalculations(List<StartupCalculation> startupCalculations) {
		this.startupCalculations = startupCalculations;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<GroupCalculation> getGroupCalculations() {
		return groupCalculations;
	}

	public void setGroupCalculations(List<GroupCalculation> groupCalculations) {
		this.groupCalculations = groupCalculations;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Question getKpi() {
		return kpi;
	}

	public void setKpi(Question kpi) {
		this.kpi = kpi;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Calculation(User user, Question kpi, LocalDateTime date, Integer numberOfStartups) {
		super();
		this.user = user;
		this.kpi = kpi;
		this.date = date;
		this.numberOfStartups = numberOfStartups;
	}

	public Calculation() {
	}

}
