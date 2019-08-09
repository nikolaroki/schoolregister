package com.iktpreobuka.schoolregister.entities.dto;

public class SchoolDTO {
	
	private String streetNumber;
	private String street;
	private String city;
	private String SchoolName;
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
	public String getSchoolName() {
		return SchoolName;
	}
	public void setSchoolName(String schoolName) {
		SchoolName = schoolName;
	}
	public SchoolDTO(String streetNumber, String street, String city, String schoolName) {
		super();
		this.streetNumber = streetNumber;
		this.street = street;
		this.city = city;
		SchoolName = schoolName;
	}
	public SchoolDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
