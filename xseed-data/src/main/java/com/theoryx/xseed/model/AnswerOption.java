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
import javax.validation.constraints.Size;

@Entity
@Table(name = "answer_options")
public class AnswerOption {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "identifier", nullable = false, unique = true)
	@Size(min = 0, max = 10)
	private String identifier;

	@Column(name = "text", nullable = false, unique = false)
	private String text;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id", referencedColumnName = "id")
	private AnswerGroup group;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public AnswerGroup getGroup() {
		return group;
	}

	public void setGroup(AnswerGroup group) {
		this.group = group;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public AnswerOption(String text, AnswerGroup group) {
		super();
		this.text = text;
		this.group = group;
	}

	public AnswerOption() {
	}

}
