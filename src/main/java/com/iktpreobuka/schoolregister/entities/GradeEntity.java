package com.iktpreobuka.schoolregister.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iktpreobuka.schoolregister.enumeration.EGradeNumber;

@Entity
@Table(name = "grade")
public class GradeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Version
	private Integer version;
	
	
	private Date schoolYear;
	private EGradeNumber gradeNumber;
	
	
	/*@JsonIgnore
	@OneToMany(mappedBy = "subject", fetch = FetchType.LAZY,
	cascade = { CascadeType.REFRESH })
	private List<GradeSubject> subjects;*/
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "school")	
	private SchoolEntity school;
	
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
    		name = "proba", 
            joinColumns = { @JoinColumn(name = "grade_id") }, 
            inverseJoinColumns = { @JoinColumn(name = "subject_id") })
	private List<SubjectEntity> subjects;
	
	
	
	

}
