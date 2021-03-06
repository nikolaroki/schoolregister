package com.iktpreobuka.schoolregister.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
public class TeacherEntity extends UserEntity {
	
	
	//Attributes
	
	private String title;
	private Double pay;
	private String pictureURL;

	
	//Constructors
	
	public TeacherEntity(Integer id, Integer version, String name, String surname, Date dateOfBirth, String address,
			List<AccountEntity> accounts, String title, Double pay, String pictureURL) {
		super(id, version, name, surname, dateOfBirth, address, accounts);
		this.title = title;
		this.pay = pay;
		this.pictureURL = pictureURL;
	}
	public TeacherEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TeacherEntity(Integer id, Integer version, String name, String surname, Date dateOfBirth, String address,
			List<AccountEntity> accounts) {
		super(id, version, name, surname, dateOfBirth, address, accounts);
		// TODO Auto-generated constructor stub
	}
	
	//GetSet
	
	
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
	public String getPictureURL() {
		return pictureURL;
	}
	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}
	
	
	
	

	
	
	
}
