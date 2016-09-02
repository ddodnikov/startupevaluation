package com.theoryx.xseed.dto;

public class UrlLabelMapping {

	private String url;
	private String label;

	public UrlLabelMapping(String url, String label) {
		this.url = url;
		this.label = label;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
