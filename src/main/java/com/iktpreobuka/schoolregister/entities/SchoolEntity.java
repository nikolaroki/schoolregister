package com.iktpreobuka.schoolregister.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "school")
public class SchoolEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Version
	private Integer version;
	
	private String schoolName;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "address")
	private AddressEntity schoolAddress;
	
	@JsonIgnore
	@OneToMany(mappedBy = "groupSchool", fetch = FetchType.LAZY,
	cascade = { CascadeType.REFRESH })
	private List<GroupEntity> groups;

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


	public List<GroupEntity> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupEntity> groups) {
		this.groups = groups;
	}

	public SchoolEntity(Integer id, Integer version, String schoolName, AddressEntity schoolAddress,
			 List<GroupEntity> groups) {
		super();
		this.id = id;
		this.version = version;
		this.schoolName = schoolName;
		this.schoolAddress = schoolAddress;
		this.groups = groups;
	}

	public SchoolEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	

	
}
