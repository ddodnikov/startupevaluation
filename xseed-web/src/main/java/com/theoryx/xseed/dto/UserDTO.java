package com.theoryx.xseed.dto;

import com.theoryx.xseed.model.UserRole;

public class UserDTO {

	private Integer id;
	private String name;
	private String email;
	private String password;
	private boolean isActive;
	private String confirmationPassword;
	private UserRole role;
	private StartupDTO startup;
	private MembershipDTO membership;

	public UserDTO() {
	}

	public UserDTO(String name, String email, String password, String confirmationPassword, boolean isActive, UserRole role) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.confirmationPassword = confirmationPassword;
		this.isActive = isActive;
		this.role = role;
	}
	
	public UserDTO(Integer id, String name, String email, String password, String confirmationPassword, boolean isActive, UserRole role) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.confirmationPassword = confirmationPassword;
		this.isActive = isActive;
		this.role = role;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmationPassword() {
		return confirmationPassword;
	}

	public void setConfirmationPassword(String confirmationPassword) {
		this.confirmationPassword = confirmationPassword;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public StartupDTO getStartup() {
		return startup;
	}

	public void setStartup(StartupDTO startup) {
		this.startup = startup;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public MembershipDTO getMembership() {
		return membership;
	}

	public void setMembership(MembershipDTO membership) {
		this.membership = membership;
	}

}
