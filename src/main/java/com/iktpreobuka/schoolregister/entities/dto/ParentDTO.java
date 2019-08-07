package com.iktpreobuka.schoolregister.entities.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iktpreobuka.schoolregister.enumeration.EUserGender;

public class ParentDTO {
	
	private String name;
	private String surname;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date dateOfBirth;
	private String email;
	private String jmbg;
	private EUserGender gender;
	private String phone;
	private String streetNumber;
	private String street;
	private String city;
	private String username;
	private String password;
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
	public EUserGender getGender() {
		return gender;
	}
	public void setGender(EUserGender gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public ParentDTO(String name, String surname, Date dateOfBirth, String email, String jmbg, EUserGender gender,
			String phone, String streetNumber, String street, String city, String username, String password) {
		super();
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.jmbg = jmbg;
		this.gender = gender;
		this.phone = phone;
		this.streetNumber = streetNumber;
		this.street = street;
		this.city = city;
		this.username = username;
		this.password = password;
	}
	public ParentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
