package com.theoryx.xseed.dto;


public class SnapshotLineDTO {
	
	private Integer id;
	private QuestionDTO question;
	private SnapshotDTO snapshot;
	private String textResponse;
	private AnswerOptionDTO selected_answer;
	private AnswerOptionDTO[] multipleSelectedAnswers;
	
	public SnapshotLineDTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public QuestionDTO getQuestion() {
		return question;
	}

	public void setQuestion(QuestionDTO question) {
		this.question = question;
	}

	public SnapshotDTO getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(SnapshotDTO snapshot) {
		this.snapshot = snapshot;
	}

	public String getTextResponse() {
		return textResponse;
	}

	public void setTextResponse(String textResponse) {
		this.textResponse = textResponse;
	}

	public AnswerOptionDTO getSelected_answer() {
		return selected_answer;
	}

	public void setSelected_answer(AnswerOptionDTO selected_answer) {
		this.selected_answer = selected_answer;
	}

	public AnswerOptionDTO[] getMultipleSelectedAnswers() {
		return multipleSelectedAnswers;
	}

	public void setMultipleSelectedAnswers(AnswerOptionDTO[] multipleSelectedAnswers) {
		this.multipleSelectedAnswers = multipleSelectedAnswers;
	}
	
}
