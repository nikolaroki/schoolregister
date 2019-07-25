package com.iktpreobuka.schoolregister.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iktpreobuka.schoolregister.enumeration.EUserGender;

@Entity
@Table(name = "student")
public class StudentEntity extends UserEntity{
	
	private EUserGender gender;
	
	private String pictureUrl;
	
	private String jmbg;

	
	@JsonIgnore
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY,
	cascade = { CascadeType.REFRESH })
	private List<StudentParent> parents;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "address")
	private AddressEntity studentAddress;

	public EUserGender getGender() {
		return gender;
	}

	public void setGender(EUserGender gender) {
		this.gender = gender;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public List<StudentParent> getParents() {
		return parents;
	}

	public void setParents(List<StudentParent> parents) {
		this.parents = parents;
	}

	public AddressEntity getAddress() {
		return studentAddress;
	}

	public void setAddress(AddressEntity address) {
		this.studentAddress = address;
	}

	public StudentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentEntity(Integer id, Integer version, String name, String surname, Date dateOfBirth,
			List<AccountEntity> accounts) {
		super(id, version, name, surname, dateOfBirth, accounts);
		// TODO Auto-generated constructor stub
	}

	public StudentEntity(EUserGender gender, String pictureUrl, String jmbg, List<StudentParent> parents,
			AddressEntity address) {
		super();
		this.gender = gender;
		this.pictureUrl = pictureUrl;
		this.jmbg = jmbg;
		this.parents = parents;
		this.studentAddress = address;
	}
	
	
	
	

	
	
	


	
	


}
