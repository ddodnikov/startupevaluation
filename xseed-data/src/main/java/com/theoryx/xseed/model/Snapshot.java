package com.theoryx.xseed.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "snapshots")
public class Snapshot {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "date", nullable = false)
	private LocalDateTime date;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "startup_id", referencedColumnName = "id")
	private Startup startup;

	@ManyToOne
	@JoinColumn(name = "survey_id", referencedColumnName = "id")
	private Survey survey;

	@OrderBy("id ASC")
	@OneToMany(mappedBy = "snapshot", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SnapshotLine> snapshotlines;

	public Snapshot() {
	}

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

	public LocalDateTime getDate() {
		return this.date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Startup getStartup() {
		return startup;
	}

	public void setStartup(Startup startup) {
		this.startup = startup;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public List<SnapshotLine> getSnapshotlines() {
		return snapshotlines;
	}

	public void setSnapshotlines(List<SnapshotLine> snapshotlines) {
		this.snapshotlines = snapshotlines;
	}

}
