package com.theoryx.xseed.dto;

import java.util.List;

public class QuestionDTO {

	private Integer id;
	private String text;
	private String type;
	private boolean hasOther;
	private boolean algo;
	private List<AnswerOptionDTO> answers;

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

	public List<AnswerOptionDTO> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerOptionDTO> answers) {
		this.answers = answers;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isHasOther() {
		return hasOther;
	}

	public void setHasOther(boolean hasOther) {
		this.hasOther = hasOther;
	}

	public boolean isAlgo() {
		return algo;
	}

	public void setAlgo(boolean algo) {
		this.algo = algo;
	}

}
