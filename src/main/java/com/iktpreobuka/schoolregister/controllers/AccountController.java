package com.iktpreobuka.schoolregister.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.schoolregister.entities.AccountEntity;
import com.iktpreobuka.schoolregister.repositories.AccountRepository;
import com.iktpreobuka.schoolregister.repositories.UserRepository;
import com.iktpreobuka.schoolregister.services.AccountDao;

@RestController
@RequestMapping(path = "/eregister/account")
@Secured("ROLE_ADMIN")
public class AccountController {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AccountDao accountDao;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		try {
			Iterable<AccountEntity> accounts = accountRepository.findAll();
			return new ResponseEntity<Iterable<AccountEntity>>(accounts, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/active")
	public ResponseEntity<?> getAllActive() {
		try {
			Iterable<AccountEntity> accounts = accountRepository.findAllActive();
			return new ResponseEntity<Iterable<AccountEntity>>(accounts, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/inactive")
	public ResponseEntity<?> getAllInactive() {
		try {
			Iterable<AccountEntity> accounts = accountRepository.findAllInactive();
			return new ResponseEntity<Iterable<AccountEntity>>(accounts, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable Integer id) {
		try {
			AccountEntity acc = accountRepository.findById(id).orElse(null);

			if (acc == null || !acc.getActive())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return new ResponseEntity<AccountEntity>(acc, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
	public ResponseEntity<?> getByUserId(@PathVariable Integer id) {
		try {

			List<AccountEntity> accounts = accountDao.findByUserId(id);

			if (accounts.isEmpty())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			return new ResponseEntity<List<AccountEntity>>(accounts, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleterUser(@PathVariable Integer id) {
		try {

			AccountEntity acc = accountRepository.findById(id).orElse(null);

			if (acc == null || !acc.getActive())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			acc.setActive(false);
			accountRepository.save(acc);
			return new ResponseEntity<AccountEntity>(acc, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
