package com.iktpreobuka.schoolregister.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import com.iktpreobuka.schoolregister.enumeration.EMarkDefinition;
import com.iktpreobuka.schoolregister.enumeration.EMarkValue;

public class MarkEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Version
	private Integer version;
	
	private EMarkValue markValue;
	
	private EMarkDefinition markDefinition;

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

	public MarkEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MarkEntity(Integer id, Integer version, EMarkValue markValue, EMarkDefinition markDefinition) {
		super();
		this.id = id;
		this.version = version;
		this.markValue = markValue;
		this.markDefinition = markDefinition;
	}
	
	
	

}
