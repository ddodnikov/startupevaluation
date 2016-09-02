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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "startups")
public class Startup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -421022650052054829L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name", nullable = false)
	@Size(min = 0, max = 45)
	private String name;

	@Column(name = "email", nullable = true)
	@Size(min = 0, max = 45)
	private String email;

	@Column(name = "phone", nullable = true)
	@Size(min = 0, max = 45)
	private String phone;

	@Column(name = "website", nullable = true)
	@Size(min = 0, max = 45)
	private String website;

	@Column(name = "vat", nullable = true)
	@Size(min = 0, max = 45)
	private String vat;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id", referencedColumnName = "id")
	private Country country;

	@OneToMany(mappedBy = "startup", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Membership> memberships;

	@OneToMany(mappedBy = "startup", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Snapshot> snapshots;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "startups")
	private List<User> users;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country counrty) {
		this.country = counrty;
	}

	public List<Membership> getMemberships() {
		return memberships;
	}

	public void setMemberships(List<Membership> memberships) {
		this.memberships = memberships;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Snapshot> getSnapshots() {
		return snapshots;
	}

	public void setSnapshots(List<Snapshot> snapshots) {
		this.snapshots = snapshots;
	}

	public Startup() {
	}

	public Startup(String name) {
		this.name = name;
	}

	public Startup(String name, String email, String phone, String website, String vat) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.website = website;
		this.vat = vat;
	}

}
