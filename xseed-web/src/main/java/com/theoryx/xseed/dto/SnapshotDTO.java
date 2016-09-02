package com.theoryx.xseed.dto;

import java.time.LocalDate;
import java.util.List;


public class SnapshotDTO {
	
	private Integer id;
	private String name;
	private LocalDate date;
	private UserDTO user;
	private StartupDTO startup;
	private SurveyDTO survey;
	private List<SnapshotLineDTO> snapshotlines;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public UserDTO getUser() {
		return user;
	}
	
	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	public StartupDTO getStartup() {
		return startup;
	}
	
	public void setStartup(StartupDTO startup) {
		this.startup = startup;
	}
	
	public SurveyDTO getSurvey() {
		return survey;
	}
	
	public void setSurvey(SurveyDTO survey) {
		this.survey = survey;
	}
	
	public List<SnapshotLineDTO> getSnapshotlines() {
		return snapshotlines;
	}
	
	public void setSnapshotlines(List<SnapshotLineDTO> snapshotlines) {
		this.snapshotlines = snapshotlines;
	}
	
	public SnapshotDTO(){
	}
	
}
