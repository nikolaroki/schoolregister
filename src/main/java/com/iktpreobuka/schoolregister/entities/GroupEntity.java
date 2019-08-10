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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.schoolregister.security.Views;

@Entity
@Table(name = "school_group")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class GroupEntity {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "group_id")
	private Integer id;
	
	@Version
	private Integer version;
	
	@JsonView(Views.Public.class)
	private Year generationYear;
	
	
	private Boolean active;
	
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "schoolGroup", fetch = FetchType.LAZY,
	cascade = { CascadeType.REFRESH })
	private List<TeacherSubjectGroup> schedules;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "grade")
	@JsonView(Views.Public.class)
	private GradeEntity groupGrade;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "school")
	@JsonView(Views.Public.class)
	private SchoolEntity groupSchool;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "home_room_teacher")
	private TeacherEntity homeRoomTeacher;
	
	@JsonIgnore
	@OneToMany(mappedBy = "schoolGroup", fetch = FetchType.LAZY,
	cascade = { CascadeType.REFRESH })
	private List<StudentEntity> students;

	
	
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

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



	public GroupEntity(Integer id, Integer version, Year generationYear, Boolean active,
			List<TeacherSubjectGroup> schedules, GradeEntity groupGrade, SchoolEntity groupSchool,
			TeacherEntity homeRoomTeacher, List<StudentEntity> students) {
		super();
		this.id = id;
		this.version = version;
		this.generationYear = generationYear;
		this.active = active;
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
