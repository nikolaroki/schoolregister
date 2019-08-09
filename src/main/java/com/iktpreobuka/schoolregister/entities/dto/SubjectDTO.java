package com.iktpreobuka.schoolregister.entities.dto;

public class SubjectDTO {
	
	private String name;
	private Integer classesPerWeek;
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
	public SubjectDTO(String name, Integer classesPerWeek) {
		super();
		this.name = name;
		this.classesPerWeek = classesPerWeek;
	}
	public SubjectDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
