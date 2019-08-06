package com.iktpreobuka.schoolregister.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.schoolregister.entities.AccountEntity;
import com.iktpreobuka.schoolregister.entities.AdminEntity;
import com.iktpreobuka.schoolregister.entities.dto.AdminDTO;
import com.iktpreobuka.schoolregister.repositories.AccountRepository;
import com.iktpreobuka.schoolregister.repositories.AdminRepository;
import com.iktpreobuka.schoolregister.repositories.RoleRepository;
import com.iktpreobuka.schoolregister.repositories.UserRepository;
import com.iktpreobuka.schoolregister.services.AccountDao;
import com.iktpreobuka.schoolregister.services.UserDao;
import com.iktpreobuka.schoolregister.util.RESTError;

@RestController
@RequestMapping(path = "/eregister/admin")
@Secured("ROLE_ADMIN")
public class AdminController {

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	UserDao userDao;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AccountDao accountDao;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllActive() {
		try {
			Iterable<AdminEntity> admins = adminRepository.findAllActive();
			return new ResponseEntity<Iterable<AdminEntity>>(admins, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/all")
	public ResponseEntity<?> getAll() {
		try {
			Iterable<AdminEntity> admins = adminRepository.findAll();
			return new ResponseEntity<Iterable<AdminEntity>>(admins, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> findAdminById(@PathVariable Integer id) {
		try {
			AdminEntity admin = adminRepository.findById(id).orElse(null);

			if (admin == null || userDao.checkifActiveAdmin(admin).isEmpty())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return new ResponseEntity<AdminEntity>(admin, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addNewAdmin(@RequestBody AdminDTO admin) {

		try {
			if(accountDao.doesAccountExists(admin.getUsername()))
				return new ResponseEntity<RESTError>(new RESTError(1, "account already exists"),HttpStatus.BAD_REQUEST);
			AccountEntity acc = new AccountEntity();
			acc.setPassword(admin.getPassword());
			acc.setUsername(admin.getUsername());
			acc.setRole(roleRepository.findById(1).orElse(null));
			acc.setActive(true);
			
			AdminEntity ae = new AdminEntity();
			if(!userDao.doesUserExists(admin.getJmbg()).isEmpty()) {
				ae = userDao.addUserToAdmin(admin.getJmbg());
				ae.setStartDate(new Date());
				ae.setName(admin.getName());
				//adminRepository.save(ae);
				return new ResponseEntity<AdminEntity>(ae, HttpStatus.CREATED);
			}
			
			ae.setStartDate(new Date());
			ae.setName(admin.getName());
			ae.setJmbg(admin.getJmbg());
			return new ResponseEntity<AdminEntity>(ae, HttpStatus.CREATED);			
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
