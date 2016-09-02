package com.theoryx.xseed.dto;

import com.theoryx.xseed.model.AnswerGroup;

public class AnswerOptionDTO {

	private Integer id;
	private String identifier;
	private String text;
	private AnswerGroup group;

	public AnswerOptionDTO() {
	}

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

}
