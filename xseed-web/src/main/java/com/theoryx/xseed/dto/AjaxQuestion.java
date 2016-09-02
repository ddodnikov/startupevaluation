package com.theoryx.xseed.dto;

import java.util.List;

public class AjaxQuestion {
	
	private Integer questionId;
	private List<Integer> answerIds;

	public List<Integer> getAnswerIds() {
		return answerIds;
	}

	public void setAnswerIds(List<Integer> answerIds) {
		this.answerIds = answerIds;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

}
