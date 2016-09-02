package com.theoryx.xseed.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="questions")
public class Question implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -285997623421846886L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="text", nullable=false)
	private String text;
	
	@Column(name="kpi", nullable=true)
	private boolean kpi;
	
	@Column(name="algo", nullable=true)
	private boolean algo;
	
	@Column(name="filter", nullable=true)
	private boolean filter;
	
	@Column(name="hasOther", nullable=false)
	private boolean hasOther;
	
	@ManyToOne
	@JoinColumn(name="question_category_id", referencedColumnName="id", nullable=false)
	private QuestionCategory category;
	
	@ManyToOne
	@JoinColumn(name="answer_group_id", referencedColumnName="id", nullable=false)
	private AnswerGroup answergroup;

	@NotNull
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name="question_group_category", 
				joinColumns={@JoinColumn(name="question_id", referencedColumnName = "id")}, 
				inverseJoinColumns={@JoinColumn(name="group_category_id", referencedColumnName = "id")})
	private List<GroupCategory> groupCategories;
	
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

	public boolean isKpi() {
		return kpi;
	}

	public void setKpi(boolean kpi) {
		this.kpi = kpi;
	}

	public boolean isAlgo() {
		return algo;
	}

	public void setAlgo(boolean algo) {
		this.algo = algo;
	}

	public boolean isFilter() {
		return filter;
	}

	public void setFilter(boolean filter) {
		this.filter = filter;
	}

	public QuestionCategory getCategory() {
		return category;
	}

	public void setCategory(QuestionCategory category) {
		this.category = category;
	}

	public AnswerGroup getAnswergroup() {
		return answergroup;
	}

	public void setAnswergroup(AnswerGroup answergroup) {
		this.answergroup = answergroup;
	}

	public boolean isHasOther() {
		return hasOther;
	}

	public void setHasOther(boolean hasOther) {
		this.hasOther = hasOther;
	}

	public List<GroupCategory> getGroupCategories() {
		return groupCategories;
	}

	public void setGroupCategories(List<GroupCategory> groupCategories) {
		this.groupCategories = groupCategories;
	}
	
}
