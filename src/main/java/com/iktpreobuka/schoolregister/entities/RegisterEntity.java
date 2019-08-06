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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iktpreobuka.schoolregister.enumeration.EMarkDefinition;
import com.iktpreobuka.schoolregister.enumeration.EMarkValue;
import com.iktpreobuka.schoolregister.enumeration.ESemester;



@Entity
@Table(name = "register")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RegisterEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "register_id")
	private Integer id;
	
	private Date registerEntryDate;
	private ESemester semester;
	
	private EMarkValue markValue;
	
	private EMarkDefinition markDefinition;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "schedule")
	private TeacherSubjectGroup schedule;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "student")
	private StudentEntity student;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getRegisterEntryDate() {
		return registerEntryDate;
	}

	public void setRegisterEntryDate(Date registerEntryDate) {
		this.registerEntryDate = registerEntryDate;
	}

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

	public RegisterEntity(Integer id, Date registerEntryDate, ESemester semester, EMarkValue markValue,
			EMarkDefinition markDefinition, TeacherSubjectGroup schedule, StudentEntity student) {
		super();
		this.id = id;
		this.registerEntryDate = registerEntryDate;
		this.semester = semester;
		this.markValue = markValue;
		this.markDefinition = markDefinition;
		this.schedule = schedule;
		this.student = student;
	}

	public RegisterEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	

	
	
	
	
	
	
	
	

}
