package com.iktpreobuka.schoolregister.entities.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TeacherDTO {
	
	private String name;
	private String surname;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date dateOfBirth;
	private String email;
	private String jmbg;
	private String username;
	private String password;
	private String title;
	private Double pay;
	
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getPay() {
		return pay;
	}
	public void setPay(Double pay) {
		this.pay = pay;
	}

	public TeacherDTO(String name, String surname, Date dateOfBirth, String email, String jmbg, String username,
			String password, String title, Double pay) {
		super();
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.jmbg = jmbg;
		this.username = username;
		this.password = password;
		this.title = title;
		this.pay = pay;
	
	}
	public TeacherDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
