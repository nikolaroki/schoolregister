package com.iktpreobuka.schoolregister.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "addresses")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AddressEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private Integer id;
	
	@Version
	private Integer version;
	
	@Column(name = "street_number")
	private String streetNumber;
	@Column(name = "street")
	private String street;
	@Column(name = "city")
	private String city;
	private Boolean active;
	
	@JsonIgnore
	@OneToMany(mappedBy = "studentAddress", fetch = FetchType.LAZY,
	cascade = { CascadeType.REFRESH })
	private List<StudentEntity> students;
	
	@JsonIgnore
	@OneToMany(mappedBy = "parentAddress", fetch = FetchType.LAZY,
	cascade = { CascadeType.REFRESH })
	private List<ParentEntity> parents;
	
	@JsonIgnore
	@OneToMany(mappedBy = "schoolAddress", fetch = FetchType.LAZY,
	cascade = { CascadeType.REFRESH })
	private List<SchoolEntity> schools;

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

	public List<StudentEntity> getStudents() {
		return students;
	}

	public void setStudents(List<StudentEntity> students) {
		this.students = students;
	}

	public List<ParentEntity> getParents() {
		return parents;
	}

	public void setParents(List<ParentEntity> parents) {
		this.parents = parents;
	}

	public List<SchoolEntity> getSchools() {
		return schools;
	}

	public void setSchools(List<SchoolEntity> schools) {
		this.schools = schools;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public AddressEntity(Integer id, Integer version, String streetNumber, String street, String city, Boolean active,
			List<StudentEntity> students, List<ParentEntity> parents, List<SchoolEntity> schools) {
		super();
		this.id = id;
		this.version = version;
		this.streetNumber = streetNumber;
		this.street = street;
		this.city = city;
		this.active = active;
		this.students = students;
		this.parents = parents;
		this.schools = schools;
	}

	public AddressEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
