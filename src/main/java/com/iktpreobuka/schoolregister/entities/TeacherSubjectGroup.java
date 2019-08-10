package com.iktpreobuka.schoolregister.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "schedule",
uniqueConstraints = { @UniqueConstraint(
		columnNames = { "teacher", "subject","schoolGroup" }) })
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TeacherSubjectGroup {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "schedule_id")
	private Integer id;
	
	private Boolean active;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "teacher")
	private TeacherEntity teacher;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "subject")
	private SubjectEntity subject;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "schoolGroup")
	private GroupEntity schoolGroup;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "schedule", fetch = FetchType.LAZY,
	cascade = { CascadeType.REFRESH })
	private List<RegisterEntity> registers;

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

	public List<RegisterEntity> getRegisters() {
		return registers;
	}

	public void setRegisters(List<RegisterEntity> registers) {
		this.registers = registers;
	}

	

	public TeacherSubjectGroup(Integer id, Boolean active, TeacherEntity teacher, SubjectEntity subject,
			GroupEntity schoolGroup, List<RegisterEntity> registers) {
		super();
		this.id = id;
		this.active = active;
		this.teacher = teacher;
		this.subject = subject;
		this.schoolGroup = schoolGroup;
		this.registers = registers;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public TeacherSubjectGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
