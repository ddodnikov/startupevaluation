package com.theoryx.xseed.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "surveys")
public class Survey {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name", nullable = false, unique = true)
	@Size(min = 0, max = 45)
	private String name;

	@OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<SurveyQuestion> questions;

	@OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Snapshot> shnapshots;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SurveyQuestion> getQuestions() {
		return this.questions;
	}

	public void setQuestions(List<SurveyQuestion> questions) {
		this.questions = questions;
	}

	public List<Snapshot> getShnapshots() {
		return shnapshots;
	}

	public void setShnapshots(List<Snapshot> shnapshots) {
		this.shnapshots = shnapshots;
	}

	public Survey(String name) {
		super();
		this.name = name;
	}

	public Survey() {
	}

}
