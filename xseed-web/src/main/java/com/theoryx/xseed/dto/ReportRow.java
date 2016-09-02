package com.theoryx.xseed.dto;

import java.util.LinkedList;

public class ReportRow {
	private String questionTitle;
	private String targetValue;
	private boolean delta;
	private LinkedList<ReportAnswer> answers;
	
	public ReportRow(){
		
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public String getTargetValue() {
		return targetValue;
	}

	public void setTargetValue(String targetValue) {
		this.targetValue = targetValue;
	}

	public boolean isDelta() {
		return delta;
	}

	public void setDelta(boolean delta) {
		this.delta = delta;
	}

	public LinkedList<ReportAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(LinkedList<ReportAnswer> answers) {
		this.answers = answers;
	}
}
