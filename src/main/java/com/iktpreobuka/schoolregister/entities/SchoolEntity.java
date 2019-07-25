package com.iktpreobuka.schoolregister.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;


@Entity
@Table(name = "school")
public class SchoolEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Version
	private Integer version;
	
	private String schoolName;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "address")
	private AddressEntity schoolAddress;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public AddressEntity getSchoolAddress() {
		return schoolAddress;
	}

	public void setSchoolAddress(AddressEntity schoolAddress) {
		this.schoolAddress = schoolAddress;
	}

	public SchoolEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SchoolEntity(Integer id, Integer version, String schoolName, AddressEntity schoolAddress) {
		super();
		this.id = id;
		this.version = version;
		this.schoolName = schoolName;
		this.schoolAddress = schoolAddress;
	}
	
	
	
	

	
	

	
}
