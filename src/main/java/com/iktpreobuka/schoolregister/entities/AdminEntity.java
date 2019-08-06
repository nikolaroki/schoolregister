package com.iktpreobuka.schoolregister.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "admin")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AdminEntity extends UserEntity {

	// Attributes
	private Date startDate;

	
	
	// GetSet
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public AdminEntity(Integer id, Integer version, String name, String surname, Date dateOfBirth, String email,
			String jmbg, List<AccountEntity> accounts, Date startDate) {
		super(id, version, name, surname, dateOfBirth, email, jmbg, accounts);
		this.startDate = startDate;
	}

	public AdminEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminEntity(Integer id, Integer version, String name, String surname, Date dateOfBirth, String email,
			String jmbg, List<AccountEntity> accounts) {
		super(id, version, name, surname, dateOfBirth, email, jmbg, accounts);
		// TODO Auto-generated constructor stub
	}

	


	

	
	

	
}
