package com.iktpreobuka.schoolregister.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class AdminEntity extends UserEntity {

	// Attributes
	private String startDate;

	// Constructors

	public AdminEntity(Integer id, Integer version, String name, String surname, Date dateOfBirth, String address,
			List<AccountEntity> accounts, String startDate) {
		super(id, version, name, surname, dateOfBirth, address, accounts);
		this.startDate = startDate;
	}

	public AdminEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminEntity(Integer id, Integer version, String name, String surname, Date dateOfBirth, String address,
			List<AccountEntity> accounts) {
		super(id, version, name, surname, dateOfBirth, address, accounts);
		// TODO Auto-generated constructor stub
	}

	// GetSet

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

}
