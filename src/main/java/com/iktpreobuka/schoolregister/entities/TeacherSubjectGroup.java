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

@Entity
@Table(name = "schedule",
uniqueConstraints = { @UniqueConstraint(
		columnNames = { "teacher", "subject","schoolGroup" }) })

public class TeacherSubjectGroup {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "schedule_id")
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "teacher")
	private TeacherEntity teacher;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "subject")
	private SubjectEntity subject;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "schoolGroup")
	private GroupEntity schoolGroup;

	public TeacherSubjectGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TeacherSubjectGroup(Integer id, TeacherEntity teacher, SubjectEntity subject, GroupEntity schoolGroup) {
		super();
		this.id = id;
		this.teacher = teacher;
		this.subject = subject;
		this.schoolGroup = schoolGroup;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TeacherEntity getTeacher() {
		return teacher;
	}

	public void setTeacher(TeacherEntity teacher) {
		this.teacher = teacher;
	}

	public SubjectEntity getSubject() {
		return subject;
	}

	public void setSubject(SubjectEntity subject) {
		this.subject = subject;
	}

	public GroupEntity getSchoolGroup() {
		return schoolGroup;
	}

	public void setSchoolGroup(GroupEntity schoolGroup) {
		this.schoolGroup = schoolGroup;
	}
	
	
	

}
