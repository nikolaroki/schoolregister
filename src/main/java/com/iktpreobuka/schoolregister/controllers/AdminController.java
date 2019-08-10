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
import com.iktpreobuka.schoolregister.entities.dto.UpdatePasswordDTO;
import com.iktpreobuka.schoolregister.entities.dto.UserBasicInfoUpdateDTO;
import com.iktpreobuka.schoolregister.repositories.AccountRepository;
import com.iktpreobuka.schoolregister.repositories.AdminRepository;
import com.iktpreobuka.schoolregister.repositories.RoleRepository;
import com.iktpreobuka.schoolregister.repositories.UserRepository;
import com.iktpreobuka.schoolregister.services.AccountDao;
import com.iktpreobuka.schoolregister.services.UserDao;
import com.iktpreobuka.schoolregister.util.Encryption;
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

			if (admin == null || userDao.getActiveAccountForAdmin(admin).isEmpty())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return new ResponseEntity<AdminEntity>(admin, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addNewAdmin(@RequestBody AdminDTO admin) {

		try {

			if (admin.getPassword() == null || admin.getPassword() == " " || admin.getPassword() == "") {
				return new ResponseEntity<RESTError>(new RESTError(2, "password not specified"),
						HttpStatus.BAD_REQUEST);
			}
			if (admin.getUsername() == null || admin.getUsername() == " " || admin.getUsername() == "") {
				return new ResponseEntity<RESTError>(new RESTError(3, "username not specified"),
						HttpStatus.BAD_REQUEST);
			}

			if (admin.getName() == null || admin.getName() == " " || admin.getName() == "") {
				return new ResponseEntity<RESTError>(new RESTError(5, "first name not specified"),
						HttpStatus.BAD_REQUEST);
			}

			if (admin.getSurname() == null || admin.getSurname() == " " || admin.getSurname() == "") {
				return new ResponseEntity<RESTError>(new RESTError(6, "last name not specified"),
						HttpStatus.BAD_REQUEST);
			}

			if (admin.getEmail() == null || admin.getEmail() == " " || admin.getEmail() == "") {
				return new ResponseEntity<RESTError>(new RESTError(7, "email not specified"), HttpStatus.BAD_REQUEST);
			}

			if (admin.getDateOfBirth() == null) {
				return new ResponseEntity<RESTError>(new RESTError(8, "please specify date of birth"),
						HttpStatus.BAD_REQUEST);
			}

			if (admin.getJmbg() == null || admin.getJmbg() == " " || admin.getJmbg() == "") {
				return new ResponseEntity<RESTError>(new RESTError(9, "JMBG not specified"), HttpStatus.BAD_REQUEST);
			}

			if (accountDao.doesAccountExists(admin.getUsername()))
				return new ResponseEntity<RESTError>(new RESTError(1, "account already exists"),
						HttpStatus.BAD_REQUEST);

			AccountEntity acc = new AccountEntity();

			acc.setPassword(Encryption.getPassEncoded(admin.getPassword()));
			acc.setUsername(admin.getUsername());
			acc.setRole(roleRepository.findById(1).orElse(null));
			acc.setActive(true);
			if (!userDao.findExistingUsers(admin.getJmbg()).isEmpty()) {
				if (!userDao.getUsersAdminAccount(userDao.findExistingUsers(admin.getJmbg()).get(0)).isEmpty())
					return new ResponseEntity<RESTError>(new RESTError(11, "user has allready an admin account"),
							HttpStatus.BAD_REQUEST);
				acc.setUser(userDao.findExistingUsers(admin.getJmbg()).get(0));
				accountRepository.save(acc);
				adminRepository.inserIntoAdminTable(userDao.findExistingUsers(admin.getJmbg()).get(0).getId(),
						new Date());
				return new ResponseEntity<String>("new account added to the existing user with id "
						+ userDao.findExistingUsers(admin.getJmbg()).get(0).getId(), HttpStatus.CREATED);
			}
			AdminEntity ae = new AdminEntity();
			ae.setStartDate(new Date());
			ae.setName(admin.getName());
			ae.setJmbg(admin.getJmbg());
			ae.setDateOfBirth(admin.getDateOfBirth());
			ae.setEmail(admin.getEmail());
			ae.setSurname(admin.getSurname());
			adminRepository.save(ae);
			acc.setUser(ae);
			accountRepository.save(acc);

			return new ResponseEntity<AdminEntity>(ae, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> updateAdminBasicInfo(@PathVariable Integer id, @RequestBody UserBasicInfoUpdateDTO admin) {
		try {
			AdminEntity adm = adminRepository.findById(id).orElse(null);
			if ((adm == null) || (userDao.getActiveAccountForAdmin(adm).get(0).getActive()) == false) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			if (admin.getName() == null && admin.getSurname() == null && admin.getDateOfBirth() == null
					&& admin.getEmail() == null && admin.getJmbg() == null)
				return new ResponseEntity<RESTError>(new RESTError(10, "attributes to be modified not specified"),
						HttpStatus.BAD_REQUEST);
			adm = userDao.checkPropToBeChangedAdmin(adm, admin);
			adminRepository.save(adm);
			return new ResponseEntity<AdminEntity>(adm, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteAdminById(@PathVariable Integer id) {
		try {
			AdminEntity adm = adminRepository.findById(id).orElse(null);
			if ((adm == null) || (userDao.getActiveAccountForAdmin(adm).get(0).getActive()) == false) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			userDao.getActiveAccountForAdmin(adm).get(0).setActive(false);
			adminRepository.save(adm);
			return new ResponseEntity<AdminEntity>(adm, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/updatePassword")
	public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordDTO adm) {
		try {
			AccountEntity acc = accountRepository.findByUsername(accountDao.getLoggedInUsername());
			if (acc.getRole().getId() != 1)
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			if (!Encryption.comparePassword(adm.getOldPassword(), acc.getPassword()))
				return new ResponseEntity<RESTError>(new RESTError(98, "wrong password"), HttpStatus.BAD_REQUEST);
			if (!adm.getNewPassword().equals(adm.getNewPasswordConf()))
				return new ResponseEntity<RESTError>(new RESTError(99, "new password not matching"),
						HttpStatus.BAD_REQUEST);
			acc.setPassword(Encryption.getPassEncoded(adm.getNewPassword()));
			accountRepository.save(acc);
			return new ResponseEntity<AdminEntity>(adminRepository.findById(acc.getUser().getId()).orElse(null),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
