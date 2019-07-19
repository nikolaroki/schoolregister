package com.iktpreobuka.schoolregister.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iktpreobuka.schoolregister.enumeration.EUserGender;

@Entity
@Table(name = "student")
public class StudentEntity extends UserEntity{
	
	private EUserGender gender;
	private String pictureURL;

	
	@JsonIgnore
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY,
	cascade = { CascadeType.REFRESH })
	private List<StudentParent> parents;

	
	public EUserGender getGender() {
		return gender;
	}

	public void setGender(EUserGender gender) {
		this.gender = gender;
	}
	
	public String getPictureURL() {
		return pictureURL;
	}

	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}

	public StudentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<StudentParent> getParents() {
		return parents;
	}

	public void setParents(List<StudentParent> parents) {
		this.parents = parents;
	}

	public StudentEntity(Integer id, Integer version, String name, String surname, Date dateOfBirth, String address,
			List<AccountEntity> accounts) {
		super(id, version, name, surname, dateOfBirth, address, accounts);
		// TODO Auto-generated constructor stub
	}

	public StudentEntity(Integer id, Integer version, String name, String surname, Date dateOfBirth, String address,
			List<AccountEntity> accounts, EUserGender gender, String pictureURL, List<StudentParent> parents) {
		super(id, version, name, surname, dateOfBirth, address, accounts);
		this.gender = gender;
		this.pictureURL = pictureURL;
		this.parents = parents;
	}




	
	


}
