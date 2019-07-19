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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "role")
public class RoleEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id")
	private Integer id;
	@Column(name="role_name")
	private String name;

	
	@JsonIgnore
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY,
	cascade = { CascadeType.REFRESH })
	private List<AccountEntity> accounts;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	


	public RoleEntity(Integer id, String name, Integer version) {
		super();
		this.id = id;
		this.name = name;

	}
	
	public RoleEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
