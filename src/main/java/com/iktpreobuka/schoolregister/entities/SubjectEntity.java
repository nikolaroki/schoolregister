package com.iktpreobuka.schoolregister.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;

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
	
	/*@JsonIgnore
	@OneToMany(mappedBy = "grade", fetch = FetchType.LAZY,
	cascade = { CascadeType.REFRESH })
	private List<GradeSubject> grades;*/
	
	@ManyToMany(mappedBy = "subjects")
	private List<GradeEntity> grades;
	
	//////////////////GetSet//////////////////	
	
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
	
    //////////////////Constructors//////////////////
	public SubjectEntity(Integer id, Integer version, String name, Integer classesPerWeek) {
		super();
		this.id = id;
		this.version = version;
		this.name = name;
		this.classesPerWeek = classesPerWeek;
	}
	public SubjectEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
}
