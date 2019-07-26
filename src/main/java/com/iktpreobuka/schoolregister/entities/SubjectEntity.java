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
@Table(name = "subject")
public class SubjectEntity {

	//////////////////Attributes//////////////////	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Version
	private Integer version;

	private String name;
	private Integer classesPerWeek;
	
	@JsonIgnore
	@OneToMany(mappedBy = "grade", fetch = FetchType.LAZY,
	cascade = { CascadeType.REFRESH })
	private List<GradeSubject> grades;
	
	@JsonIgnore
	@OneToMany(mappedBy = "subject", fetch = FetchType.LAZY,
	cascade = { CascadeType.REFRESH })
	private List<TeacherSubjectGroup> schedules;

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

	public Integer getClassesPerWeek() {
		return classesPerWeek;
	}

	public void setClassesPerWeek(Integer classesPerWeek) {
		this.classesPerWeek = classesPerWeek;
	}

	public List<GradeSubject> getGrades() {
		return grades;
	}

	public void setGrades(List<GradeSubject> grades) {
		this.grades = grades;
	}

	public SubjectEntity(Integer id, Integer version, String name, Integer classesPerWeek, List<GradeSubject> grades) {
		super();
		this.id = id;
		this.version = version;
		this.name = name;
		this.classesPerWeek = classesPerWeek;
		this.grades = grades;
	}

	public SubjectEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
	

	
}
