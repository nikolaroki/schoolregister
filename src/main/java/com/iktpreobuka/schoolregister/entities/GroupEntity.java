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
import javax.persistence.Version;

@Entity
@Table(name = "school_group")
public class GroupEntity {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "group_id")
	private Integer id;
	
	@Version
	private Integer version;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "grade")
	private GradeEntity groupGrade;

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

	public GradeEntity getGroupGrade() {
		return groupGrade;
	}

	public void setGroupGrade(GradeEntity groupGrade) {
		this.groupGrade = groupGrade;
	}

	public GroupEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GroupEntity(Integer id, Integer version, GradeEntity groupGrade) {
		super();
		this.id = id;
		this.version = version;
		this.groupGrade = groupGrade;
	}
	
	
	
	

}
