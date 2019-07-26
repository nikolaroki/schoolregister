package com.iktpreobuka.schoolregister.entities;

import java.util.Date;

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

import com.iktpreobuka.schoolregister.enumeration.ESemester;

@Entity
@Table(name = "register")
public class RegisterEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "register_id")
	private Integer id;
	
	private Date registerEntry;
	private ESemester semester;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "schedule")
	private TeacherSubjectGroup schedule;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "student")
	private StudentEntity student;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "mark")
	private MarkEntity mark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getRegisterEntry() {
		return registerEntry;
	}

	public void setRegisterEntry(Date registerEntry) {
		this.registerEntry = registerEntry;
	}

	public ESemester getSemester() {
		return semester;
	}

	public void setSemester(ESemester semester) {
		this.semester = semester;
	}

	public TeacherSubjectGroup getSchedule() {
		return schedule;
	}

	public void setSchedule(TeacherSubjectGroup schedule) {
		this.schedule = schedule;
	}

	public StudentEntity getStudent() {
		return student;
	}

	public void setStudent(StudentEntity student) {
		this.student = student;
	}

	public MarkEntity getMark() {
		return mark;
	}

	public void setMark(MarkEntity mark) {
		this.mark = mark;
	}

	public RegisterEntity(Integer id, Date registerEntry, ESemester semester, TeacherSubjectGroup schedule,
			StudentEntity student, MarkEntity mark) {
		super();
		this.id = id;
		this.registerEntry = registerEntry;
		this.semester = semester;
		this.schedule = schedule;
		this.student = student;
		this.mark = mark;
	}

	public RegisterEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	
	
	
	

}
