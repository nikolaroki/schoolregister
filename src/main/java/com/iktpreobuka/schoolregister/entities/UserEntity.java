package com.iktpreobuka.schoolregister.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user",uniqueConstraints = { @UniqueConstraint(columnNames = { "email", "jmbg" }) })
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer id;
	
	@Version
	private Integer version;
	
	@Column(name = "name")
	//@NotNull(message = "first name not specified")
	private String name;
	@Column(name = "surname")
	//@NotNull(message = "last name not specified")
	private String surname;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name = "dob")
	private Date dateOfBirth;
	//@NotNull(message = "email not specified")
	//@Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$",
	//message="email format not valid.")
	@Column(name = "email")
	private String email;
	//@Size(min=13, max=14, message = "JMBF has 13 numbers.")
	@Column(name = "jmbg")
	
	private String jmbg;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
	cascade = { CascadeType.REFRESH })
	private List<AccountEntity> accounts;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public List<AccountEntity> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountEntity> accounts) {
		this.accounts = accounts;
	}

	

	public UserEntity(Integer id, Integer version, String name, String surname, Date dateOfBirth, String email,
			String jmbg, List<AccountEntity> accounts) {
		super();
		this.id = id;
		this.version = version;
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.jmbg = jmbg;
		this.accounts = accounts;
	}

	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	
	
	

}
