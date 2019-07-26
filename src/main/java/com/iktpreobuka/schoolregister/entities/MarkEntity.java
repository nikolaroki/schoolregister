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
import com.iktpreobuka.schoolregister.enumeration.EMarkDefinition;
import com.iktpreobuka.schoolregister.enumeration.EMarkValue;

@Entity
@Table(name = "mark")
public class MarkEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Version
	private Integer version;
	
	private EMarkValue markValue;
	
	private EMarkDefinition markDefinition;
	
	@JsonIgnore
	@OneToMany(mappedBy = "mark", fetch = FetchType.LAZY,
	cascade = { CascadeType.REFRESH })
	private List<RegisterEntity> registers;

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

	public List<RegisterEntity> getRegisters() {
		return registers;
	}

	public void setRegisters(List<RegisterEntity> registers) {
		this.registers = registers;
	}

	public MarkEntity(Integer id, Integer version, EMarkValue markValue, EMarkDefinition markDefinition,
			List<RegisterEntity> registers) {
		super();
		this.id = id;
		this.version = version;
		this.markValue = markValue;
		this.markDefinition = markDefinition;
		this.registers = registers;
	}

	public MarkEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	

}
