package com.iktpreobuka.schoolregister.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iktpreobuka.schoolregister.enumeration.EUserGender;

@Entity
@Table(name = "student")
public class StudentEntity extends UserEntity{
	
	private EUserGender gender;
	
	private String pictureUrl;
	
	private String jmbg;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "schoolGroup")
	private GroupEntity schoolGroup;
	
	@JsonIgnore
	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY,
	cascade = { CascadeType.REFRESH })
	private List<RegisterEntity> registers;

	
	@JsonIgnore
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY,
	cascade = { CascadeType.REFRESH })
	private List<StudentParent> parents;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "address")
	private AddressEntity studentAddress;

	public EUserGender getGender() {
		return gender;
	}

	public void setGender(EUserGender gender) {
		this.gender = gender;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
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

	public List<StudentParent> getParents() {
		return parents;
	}

	public void setParents(List<StudentParent> parents) {
		this.parents = parents;
	}

	public AddressEntity getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(AddressEntity studentAddress) {
		this.studentAddress = studentAddress;
	}

	public StudentEntity(Integer id, Integer version, String name, String surname, Date dateOfBirth, String email,
			String jmbg, List<AccountEntity> accounts, EUserGender gender, String pictureUrl, String jmbg2,
			GroupEntity schoolGroup, List<RegisterEntity> registers, List<StudentParent> parents,
			AddressEntity studentAddress) {
		super(id, version, name, surname, dateOfBirth, email, jmbg, accounts);
		this.gender = gender;
		this.pictureUrl = pictureUrl;
		jmbg = jmbg2;
		this.schoolGroup = schoolGroup;
		this.registers = registers;
		this.parents = parents;
		this.studentAddress = studentAddress;
	}

	public StudentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentEntity(Integer id, Integer version, String name, String surname, Date dateOfBirth, String email,
			String jmbg, List<AccountEntity> accounts) {
		super(id, version, name, surname, dateOfBirth, email, jmbg, accounts);
		// TODO Auto-generated constructor stub
	}

	

	

	
	
	
	

	
	
	


	
	


}
