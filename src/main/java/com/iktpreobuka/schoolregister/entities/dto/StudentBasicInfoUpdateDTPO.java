package com.iktpreobuka.schoolregister.entities.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iktpreobuka.schoolregister.enumeration.EUserGender;

public class StudentBasicInfoUpdateDTPO {

	private String name;
	private String surname;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date dateOfBirth;
	private String email;
	private String jmbg;
	private EUserGender gender;
	private String streetNumber;
	private String street;
	private String city;
	
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
	public StudentBasicInfoUpdateDTPO(String name, String surname, Date dateOfBirth, String email, String jmbg, EUserGender gender, String streetNumber, String street, String city) {
		super();
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.jmbg = jmbg;
		this.gender = gender;
		this.streetNumber = streetNumber;
		this.street = street;
		this.city = city;
	}
	public StudentBasicInfoUpdateDTPO() {
		super();
		// TODO Auto-generated constructor stub
	}
}
