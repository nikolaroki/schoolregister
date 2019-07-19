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
@Entity
@Table(name = "child_parent")
public class StudentParent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "child_parent_id")
	private Integer id;
	
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "child")	
	private StudentEntity child;
	
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "parent")	
	private ParentEntity parent;

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
	//	this.id = id;
		this.child = child;
		this.parent = parent;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
}

	public StudentParent() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
