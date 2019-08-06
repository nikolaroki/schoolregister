package com.iktpreobuka.schoolregister.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "child_parent", uniqueConstraints = { @UniqueConstraint(columnNames = { "children", "parents" }) })
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class StudentParent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "parent_child_id")
	private Integer id;
	

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "children")
	private StudentEntity child;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "parents")
	private ParentEntity parent;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StudentEntity getChild() {
		return child;
	}

	public void setChild(StudentEntity child) {
		this.child = child;
	}

	public ParentEntity getParent() {
		return parent;
	}

	public void setParent(ParentEntity parent) {
		this.parent = parent;
	}

	public StudentParent(Integer id, StudentEntity child, ParentEntity parent) {
		super();
		this.id = id;
		this.child = child;
		this.parent = parent;
	}

	public StudentParent() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	

}
