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
@Table(name = "parent")
public class ParentEntity extends UserEntity{
	
	private EUserGender gender;
	
	private String email;
	
	

	public EUserGender getGender() {
		return gender;
	}

	public void setGender(EUserGender gender) {
		this.gender = gender;
	}

	

	
	@JsonIgnore
	@OneToMany(mappedBy = "child", fetch = FetchType.LAZY,
	cascade = { CascadeType.REFRESH })
	private List<StudentParent> children;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "address")
	private AddressEntity parentAddress;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<StudentParent> getChildren() {
		return children;
	}

	public void setChildren(List<StudentParent> children) {
		this.children = children;
	}

	public AddressEntity getAddress() {
		return parentAddress;
	}

	public void setAddress(AddressEntity address) {
		this.parentAddress = address;
	}

	public ParentEntity(EUserGender gender, String email, List<StudentParent> children, AddressEntity address) {
		super();
		this.gender = gender;
		this.email = email;
		this.children = children;
		this.parentAddress = address;
	}

	public ParentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ParentEntity(Integer id, Integer version, String name, String surname, Date dateOfBirth,
			List<AccountEntity> accounts) {
		super(id, version, name, surname, dateOfBirth, accounts);
		// TODO Auto-generated constructor stub
	}
	
	
	
	

	
	
	
	

}
