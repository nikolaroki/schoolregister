package com.iktpreobuka.schoolregister.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "school")
public class SchoolEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Version
	private Integer version;
	
	private String name;
	private String address;
	
	@JsonIgnore
	@OneToMany(mappedBy = "school", fetch = FetchType.LAZY,
	cascade = { CascadeType.REFRESH })
	private List<GradeEntity> grades;
	
	public SchoolEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SchoolEntity(Integer id, Integer version, String name, String address) {
		super();
		this.id = id;
		this.version = version;
		this.name = name;
		this.address = address;
	}
	
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	
}
