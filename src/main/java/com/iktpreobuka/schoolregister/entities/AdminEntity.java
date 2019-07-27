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

	
	
	// GetSet
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public AdminEntity(Integer id, Integer version, String name, String surname, Date dateOfBirth, String email,
			List<AccountEntity> accounts, Date startDate) {
		super(id, version, name, surname, dateOfBirth, email, accounts);
		this.startDate = startDate;
	}

	public AdminEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminEntity(Integer id, Integer version, String name, String surname, Date dateOfBirth, String email,
			List<AccountEntity> accounts) {
		super(id, version, name, surname, dateOfBirth, email, accounts);
		// TODO Auto-generated constructor stub
	}
	


	

	
	

	
}
