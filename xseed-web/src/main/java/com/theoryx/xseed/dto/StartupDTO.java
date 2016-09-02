package com.theoryx.xseed.dto;

import java.util.List;

public class StartupDTO {

	private Integer id;
	private String name;
	private String email;
	private String phone;
	private String website;
	private String vat;
	private CountryDTO country;
	private List<UserDTO> users;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<UserDTO> getUsers() {
		return users;
	}

	public void setUsers(List<UserDTO> users) {
		this.users = users;
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

	public CountryDTO getCountry() {
		return country;
	}

	public void setCountry(CountryDTO country) {
		this.country = country;
	}

	public StartupDTO(String name, String email, String phone, String website, String vat) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.website = website;
		this.vat = vat;
	}

	public StartupDTO() {
	}

}
