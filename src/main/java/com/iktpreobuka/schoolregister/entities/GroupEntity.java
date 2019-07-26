package com.iktpreobuka.schoolregister.entities;

import java.time.Year;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "school_group")
public class GroupEntity {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "group_id")
	private Integer id;
	
	@Version
	private Integer version;
	
	private Year generationYear;
	
	@JsonIgnore
	@OneToMany(mappedBy = "schoolGroup", fetch = FetchType.LAZY,
	cascade = { CascadeType.REFRESH })
	private List<TeacherSubjectGroup> schedules;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "grade")
	private GradeEntity groupGrade;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "school")
	private SchoolEntity groupSchool;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "home_room_teacher")
	private TeacherEntity homeRoomTeacher;
	
	@JsonIgnore
	@OneToMany(mappedBy = "schoolGroup", fetch = FetchType.LAZY,
	cascade = { CascadeType.REFRESH })
	private List<StudentEntity> students;

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

	public Year getGenerationYear() {
		return generationYear;
	}

	public void setGenerationYear(Year generationYear) {
		this.generationYear = generationYear;
	}

	public List<TeacherSubjectGroup> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<TeacherSubjectGroup> schedules) {
		this.schedules = schedules;
	}

	public GradeEntity getGroupGrade() {
		return groupGrade;
	}

	public void setGroupGrade(GradeEntity groupGrade) {
		this.groupGrade = groupGrade;
	}

	public SchoolEntity getGroupSchool() {
		return groupSchool;
	}

	public void setGroupSchool(SchoolEntity groupSchool) {
		this.groupSchool = groupSchool;
	}

	public TeacherEntity getHomeRoomTeacher() {
		return homeRoomTeacher;
	}

	public void setHomeRoomTeacher(TeacherEntity homeRoomTeacher) {
		this.homeRoomTeacher = homeRoomTeacher;
	}

	public List<StudentEntity> getStudents() {
		return students;
	}

	public void setStudents(List<StudentEntity> students) {
		this.students = students;
	}

	public GroupEntity(Integer id, Integer version, Year generationYear, List<TeacherSubjectGroup> schedules,
			GradeEntity groupGrade, SchoolEntity groupSchool, TeacherEntity homeRoomTeacher,
			List<StudentEntity> students) {
		super();
		this.id = id;
		this.version = version;
		this.generationYear = generationYear;
		this.schedules = schedules;
		this.groupGrade = groupGrade;
		this.groupSchool = groupSchool;
		this.homeRoomTeacher = homeRoomTeacher;
		this.students = students;
	}

	public GroupEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
