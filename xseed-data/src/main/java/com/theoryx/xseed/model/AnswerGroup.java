package com.theoryx.xseed.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "answer_groups")
public class AnswerGroup {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "identifier", nullable = false, unique = true)
	@Size(min = 0, max = 10)
	private String identifier;

	@Column(name = "name", nullable = true, unique = false)
	@Size(min = 0, max = 45)
	private String name;

	@Column(name = "type", nullable = true, unique = false)
	@Enumerated(EnumType.STRING)
	@Size(min = 0, max = 45)
	private QuestionAnswerType type;

	@OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<AnswerOption> options;

	@OneToMany(mappedBy = "answergroup", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Question> questions;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public QuestionAnswerType getType() {
		return type;
	}

	public void setType(QuestionAnswerType type) {
		this.type = type;
	}

	public List<AnswerOption> getOptions() {
		return this.options;
	}

	public void setOptions(List<AnswerOption> options) {
		this.options = options;
	}

	public List<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public AnswerGroup(String identifier, String name) {
		super();
		this.identifier = identifier;
		this.name = name;
	}

	public AnswerGroup() {
	}

}
