package com.theoryx.xseed.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "snapshotlines")
public class SnapshotLine {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "question_id", referencedColumnName = "id")
	private Question question;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "snapshot_id", referencedColumnName = "id")
	private Snapshot snapshot;

	@Column(name = "text_response", nullable = true)
	private String textResponse;

	@ManyToOne
	@JoinColumn(name = "selected_answer_id", referencedColumnName = "id", nullable = true)
	private AnswerOption selected_answer;

	@Column(name = "multiple_answers_id", nullable = true)
	@Type(type = "com.theoryx.xseed.model.AnswerArrayType")
	private AnswerOption[] multipleSelectedAnswers;

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

	public Snapshot getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(Snapshot snapshot) {
		this.snapshot = snapshot;
	}

	public String getTextResponse() {
		return textResponse;
	}

	public void setTextResponse(String textResponse) {
		this.textResponse = textResponse;
	}

	public AnswerOption getSelected_answer() {
		return selected_answer;
	}

	public void setSelected_answer(AnswerOption selected_answer) {
		this.selected_answer = selected_answer;
	}

	public AnswerOption[] getMultipleSelectedAnswers() {
		return multipleSelectedAnswers;
	}

	public void setMultipleSelectedAnswers(AnswerOption[] multipleSelectedAnswers) {
		this.multipleSelectedAnswers = multipleSelectedAnswers;
	}

}