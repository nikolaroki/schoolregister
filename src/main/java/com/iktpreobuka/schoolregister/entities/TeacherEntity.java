package com.iktpreobuka.schoolregister.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "teacher")
public class TeacherEntity extends UserEntity {
	
	
	//Attributes
	
	private String title;
	private Double pay;
	private String pictureURL;
	
	@JsonIgnore
	@OneToMany(mappedBy = "school", fetch = FetchType.LAZY,
	cascade = { CascadeType.REFRESH })
	private List<TeacherSchool> schools;

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

	public List<TeacherSchool> getSchools() {
		return schools;
	}

	public void setSchools(List<TeacherSchool> schools) {
		this.schools = schools;
	}

	public TeacherEntity(String title, Double pay, String pictureURL, List<TeacherSchool> schools) {
		super();
		this.title = title;
		this.pay = pay;
		this.pictureURL = pictureURL;
		this.schools = schools;
	}

	public TeacherEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TeacherEntity(Integer id, Integer version, String name, String surname, Date dateOfBirth,
			List<AccountEntity> accounts) {
		super(id, version, name, surname, dateOfBirth, accounts);
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	

	
	
	
	
	

	
	
	
}
