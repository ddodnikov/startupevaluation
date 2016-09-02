package com.theoryx.xseed.dto;

public class ReportAnswer {
	private String answer;
	private Double value;
	
	public ReportAnswer(){
		
	}
	
	public ReportAnswer(String answer, Double value){
		this.answer = answer;
		this.value = value;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	

}
