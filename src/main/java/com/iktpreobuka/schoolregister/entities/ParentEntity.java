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
	
	
	private String phone;
	
	@JsonIgnore
	@OneToMany(mappedBy = "child", fetch = FetchType.LAZY,
	cascade = { CascadeType.REFRESH })
	private List<StudentParent> children;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "address")
	private AddressEntity parentAddress;

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

	public List<StudentParent> getChildren() {
		return children;
	}

	public void setChildren(List<StudentParent> children) {
		this.children = children;
	}

	public AddressEntity getParentAddress() {
		return parentAddress;
	}

	public void setParentAddress(AddressEntity parentAddress) {
		this.parentAddress = parentAddress;
	}

	public ParentEntity(Integer id, Integer version, String name, String surname, Date dateOfBirth, String email,
			List<AccountEntity> accounts, EUserGender gender, String phone, List<StudentParent> children,
			AddressEntity parentAddress) {
		super(id, version, name, surname, dateOfBirth, email, accounts);
		this.gender = gender;
		this.phone = phone;
		this.children = children;
		this.parentAddress = parentAddress;
	}

	public ParentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ParentEntity(Integer id, Integer version, String name, String surname, Date dateOfBirth, String email,
			List<AccountEntity> accounts) {
		super(id, version, name, surname, dateOfBirth, email, accounts);
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	
	
	

	
	
	
	

}
