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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "grade")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class GradeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "groupGrade", fetch = FetchType.LAZY,
	cascade = { CascadeType.REFRESH })
	private List<GroupEntity> groups;

	@JsonIgnore
	@OneToMany(mappedBy = "subject", fetch = FetchType.LAZY,
	cascade = { CascadeType.REFRESH })
	private List<GradeSubject> subjects;

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

	public List<GroupEntity> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupEntity> groups) {
		this.groups = groups;
	}

	public List<GradeSubject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<GradeSubject> subjects) {
		this.subjects = subjects;
	}

	public GradeEntity(Integer id, String gradeName, List<GroupEntity> groups, List<GradeSubject> subjects) {
		super();
		this.id = id;
		this.name = gradeName;
		this.groups = groups;
		this.subjects = subjects;
	}

	public GradeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
	
	
	
	
	

}
