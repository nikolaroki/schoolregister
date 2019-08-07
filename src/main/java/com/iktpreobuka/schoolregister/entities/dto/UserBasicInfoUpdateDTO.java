package com.iktpreobuka.schoolregister.entities.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserBasicInfoUpdateDTO {
	
	private String name;
	private String surname;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date dateOfBirth;
	private String email;
	private String jmbg;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJmbg() {
		return jmbg;
	}
	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}
	public UserBasicInfoUpdateDTO(String name, String surname, Date dateOfBirth, String email, String jmbg) {
		super();
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.jmbg = jmbg;
	}
	public UserBasicInfoUpdateDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	

}
