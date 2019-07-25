package com.iktpreobuka.schoolregister.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class AdminEntity extends UserEntity {

	// Attributes
	private Date startDate;



	// Constructors

	public AdminEntity(Date startDate) {
		super();
		this.startDate = startDate;
	}

	public AdminEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminEntity(Integer id, Integer version, String name, String surname, Date dateOfBirth,
			List<AccountEntity> accounts) {
		super(id, version, name, surname, dateOfBirth, accounts);
		// TODO Auto-generated constructor stub
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	


	// GetSet
	
	

	
}
