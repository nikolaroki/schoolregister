package com.iktpreobuka.schoolregister.entities.dto;

import com.iktpreobuka.schoolregister.enumeration.EMarkDefinition;
import com.iktpreobuka.schoolregister.enumeration.EMarkValue;
import com.iktpreobuka.schoolregister.enumeration.ESemester;

public class MarkDTO {

	private ESemester semester;
	private EMarkValue markValue;
	private EMarkDefinition markDefinition;
	
	
	public ESemester getSemester() {
		return semester;
	}
	public void setSemester(ESemester semester) {
		this.semester = semester;
	}
	public EMarkValue getMarkValue() {
		return markValue;
	}
	public void setMarkValue(EMarkValue markValue) {
		this.markValue = markValue;
	}
	public EMarkDefinition getMarkDefinition() {
		return markDefinition;
	}
	public void setMarkDefinition(EMarkDefinition markDefinition) {
		this.markDefinition = markDefinition;
	}
	public MarkDTO(ESemester semester, EMarkValue markValue, EMarkDefinition markDefinition) {
		super();
		this.semester = semester;
		this.markValue = markValue;
		this.markDefinition = markDefinition;
	}
	public MarkDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
