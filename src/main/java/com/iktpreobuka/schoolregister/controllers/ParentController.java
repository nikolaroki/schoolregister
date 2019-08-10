package com.iktpreobuka.schoolregister.controllers;

import java.util.List;

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
import com.iktpreobuka.schoolregister.entities.AddressEntity;
import com.iktpreobuka.schoolregister.entities.ParentEntity;
import com.iktpreobuka.schoolregister.entities.StudentEntity;
import com.iktpreobuka.schoolregister.entities.dto.ParentDTO;
import com.iktpreobuka.schoolregister.entities.dto.ParentUpdateDTO;
import com.iktpreobuka.schoolregister.entities.dto.UpdatePasswordDTO;
import com.iktpreobuka.schoolregister.repositories.AccountRepository;
import com.iktpreobuka.schoolregister.repositories.AddressRepository;
import com.iktpreobuka.schoolregister.repositories.ChildParentRepository;
import com.iktpreobuka.schoolregister.repositories.ParentRepository;
import com.iktpreobuka.schoolregister.repositories.RoleRepository;
import com.iktpreobuka.schoolregister.repositories.StudentRepository;
import com.iktpreobuka.schoolregister.services.AccountDao;
import com.iktpreobuka.schoolregister.services.AddressDao;
import com.iktpreobuka.schoolregister.services.UserDao;
import com.iktpreobuka.schoolregister.util.Encryption;
import com.iktpreobuka.schoolregister.util.RESTError;

@RestController
@RequestMapping(path = "/eregister/parent")
public class ParentController {

	@Autowired
	private ParentRepository parentRepository;

	@Autowired
	private UserDao userDao;

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ChildParentRepository childParentRepository;

	@RequestMapping(method = RequestMethod.GET)
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> getAllActive() {
		try {
			Iterable<ParentEntity> parents = parentRepository.findAllActive();
			return new ResponseEntity<Iterable<ParentEntity>>(parents, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/all")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> getAll() {
		try {
			Iterable<ParentEntity> parents = parentRepository.findAll();
			return new ResponseEntity<Iterable<ParentEntity>>(parents, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	
	public ResponseEntity<?> findParentById(@PathVariable Integer id) {
		try {
			ParentEntity parent = parentRepository.findById(id).orElse(null);

			if (parent == null || userDao.getActiveAccountForParent(parent).isEmpty())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return new ResponseEntity<ParentEntity>(parent, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> addNewAdmin(@RequestBody ParentDTO parent) {

		try {

			if (parent.getPassword() == null || parent.getPassword() == " " || parent.getPassword() == "") {
				return new ResponseEntity<RESTError>(new RESTError(2, "password not specified"),
						HttpStatus.BAD_REQUEST);
			}
			if (parent.getUsername() == null || parent.getUsername() == " " || parent.getUsername() == "") {
				return new ResponseEntity<RESTError>(new RESTError(3, "username not specified"),
						HttpStatus.BAD_REQUEST);
			}

			if (parent.getName() == null || parent.getName() == " " || parent.getName() == "") {
				return new ResponseEntity<RESTError>(new RESTError(5, "first name not specified"),
						HttpStatus.BAD_REQUEST);
			}

			if (parent.getSurname() == null || parent.getSurname() == " " || parent.getSurname() == "") {
				return new ResponseEntity<RESTError>(new RESTError(6, "last name not specified"),
						HttpStatus.BAD_REQUEST);
			}

			if (parent.getEmail() == null || parent.getEmail() == " " || parent.getEmail() == "") {
				return new ResponseEntity<RESTError>(new RESTError(7, "email not specified"), HttpStatus.BAD_REQUEST);
			}

			if (parent.getDateOfBirth() == null) {
				return new ResponseEntity<RESTError>(new RESTError(8, "please specify date of birth"),
						HttpStatus.BAD_REQUEST);
			}

			if (parent.getJmbg() == null || parent.getJmbg() == " " || parent.getJmbg() == "") {
				return new ResponseEntity<RESTError>(new RESTError(9, "JMBG not specified"), HttpStatus.BAD_REQUEST);
			}

			if (parent.getPhone() == null || parent.getPhone() == " " || parent.getPhone() == "") {
				return new ResponseEntity<RESTError>(new RESTError(10, "phone number not specified"),
						HttpStatus.BAD_REQUEST);
			}

			if (parent.getStreetNumber() == null || parent.getStreetNumber() == " " || parent.getStreetNumber() == "") {
				return new ResponseEntity<RESTError>(new RESTError(11, "street number not specified"),
						HttpStatus.BAD_REQUEST);
			}

			if (parent.getStreet() == null || parent.getStreet() == " " || parent.getStreet() == "") {
				return new ResponseEntity<RESTError>(new RESTError(12, "street not specified"), HttpStatus.BAD_REQUEST);
			}

			if (parent.getCity() == null || parent.getCity() == " " || parent.getCity() == "") {
				return new ResponseEntity<RESTError>(new RESTError(13, "city not specified"), HttpStatus.BAD_REQUEST);
			}

			if (parent.getGender() == null) {
				return new ResponseEntity<RESTError>(new RESTError(14, "gender not specified"), HttpStatus.BAD_REQUEST);
			}

			if (accountDao.doesAccountExists(parent.getUsername()))
				return new ResponseEntity<RESTError>(new RESTError(1, "account already exists"),
						HttpStatus.BAD_REQUEST);

			AccountEntity acc = new AccountEntity();
			acc.setPassword(Encryption.getPassEncoded(parent.getPassword()));
			acc.setUsername(parent.getUsername());
			acc.setRole(roleRepository.findById(4).orElse(null));
			acc.setActive(true);

			AddressEntity adr = new AddressEntity();
			adr.setCity(parent.getCity());
			adr.setStreet(parent.getStreet());
			adr.setStreetNumber(parent.getStreetNumber());
			adr.setActive(true);

			if (!addressDao.findIfExists(adr).isEmpty())
				adr = addressDao.findIfExists(adr).get(0);
			if (!adr.getActive())
				adr.setActive(true);

			if (!userDao.findExistingUsers(parent.getJmbg()).isEmpty()) {
				if (!userDao.getUsersParentAccount(userDao.findExistingUsers(parent.getJmbg()).get(0)).isEmpty())
					return new ResponseEntity<RESTError>(new RESTError(15, "user has allready an parent account"),
							HttpStatus.BAD_REQUEST);
				acc.setUser(userDao.findExistingUsers(parent.getJmbg()).get(0));
				accountRepository.save(acc);
				addressRepository.save(adr);
				parentRepository.inserIntoParentTable(userDao.findExistingUsers(parent.getJmbg()).get(0).getId(),
						adr.getId(), parent.getPhone(), parent.getGender().ordinal());
				return new ResponseEntity<String>("new account added to the existing user with id "
						+ userDao.findExistingUsers(parent.getJmbg()).get(0).getId(), HttpStatus.CREATED);
			}
			ParentEntity prt = new ParentEntity();
			prt.setName(parent.getName());
			prt.setJmbg(parent.getJmbg());
			prt.setDateOfBirth(parent.getDateOfBirth());
			prt.setEmail(parent.getEmail());
			prt.setSurname(parent.getSurname());
			prt.setGender(parent.getGender());
			addressRepository.save(adr);
			prt.setParentAddress(adr);
			prt.setPhone(parent.getPhone());
			parentRepository.save(prt);
			acc.setUser(prt);
			accountRepository.save(acc);

			return new ResponseEntity<ParentEntity>(prt, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	
	public ResponseEntity<?> updateParentBasicInfo(@PathVariable Integer id, @RequestBody ParentUpdateDTO parent) {
		try {
			ParentEntity prt = parentRepository.findById(id).orElse(null);
			if ((prt == null) || (!userDao.getActiveAccountForParent(prt).get(0).getActive())) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			if (parent.getName() == null && parent.getSurname() == null && parent.getDateOfBirth() == null
					&& parent.getEmail() == null && parent.getJmbg() == null && parent.getGender() == null
					&& parent.getPhone() == null && parent.getStreetNumber() == null && parent.getStreet() == null
					&& parent.getCity() == null)
				return new ResponseEntity<RESTError>(new RESTError(16, "attributes to be modified not specified"),
						HttpStatus.BAD_REQUEST);
			prt = userDao.checkPropToBeChangedParent(prt, parent);
			addressRepository.save(prt.getParentAddress());
			parentRepository.save(prt);
			return new ResponseEntity<ParentEntity>(prt, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteParentById(@PathVariable Integer id) {
		try {
			ParentEntity prt = parentRepository.findById(id).orElse(null);
			if ((prt == null) || (!userDao.getActiveAccountForParent(prt).get(0).getActive())) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			userDao.getActiveAccountForParent(prt).get(0).setActive(false);
			parentRepository.save(prt);
			return new ResponseEntity<ParentEntity>(prt, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/updatePassword")
	@Secured("ROLE_PARENT")
	public ResponseEntity<?> updatePassword (@RequestBody UpdatePasswordDTO utdpsw){
		try {
			AccountEntity acc = accountRepository.findByUsername(accountDao.getLoggedInUsername());
			if(acc.getRole().getId() != 4)
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			if(!Encryption.comparePassword(utdpsw.getOldPassword(), acc.getPassword()))
				return new ResponseEntity<RESTError>(new RESTError(98, "wrong password"),
						HttpStatus.BAD_REQUEST);
			if(!utdpsw.getNewPassword().equals(utdpsw.getNewPasswordConf()))
				return new ResponseEntity<RESTError>(new RESTError(99, "new password not matching"),
						HttpStatus.BAD_REQUEST);
			acc.setPassword(Encryption.getPassEncoded(utdpsw.getNewPassword()));
			accountRepository.save(acc);
			return new ResponseEntity<ParentEntity>(parentRepository.findById(acc.getUser().getId()).orElse(null), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
				

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/parents/{id}")
	public ResponseEntity<?> getParentsFromChild(@PathVariable("id") Integer id) {
		try {
			StudentEntity child = studentRepository.findById(id).orElse(null);
			if (child == null || userDao.getActiveAccountForStudent(child).isEmpty())
				return new ResponseEntity<RESTError>(new RESTError(16, "child not found"), HttpStatus.NOT_FOUND);
			List<ParentEntity> parents = childParentRepository.findParentsByChild(child);
			if(parents.isEmpty())
				return new ResponseEntity<RESTError>(new RESTError(20, "child has no parents"), HttpStatus.NOT_FOUND);
			return new ResponseEntity<List<ParentEntity>>(parents, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

}
