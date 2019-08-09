package com.iktpreobuka.schoolregister.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.iktpreobuka.schoolregister.entities.AccountEntity;
import com.iktpreobuka.schoolregister.entities.TeacherEntity;
import com.iktpreobuka.schoolregister.entities.dto.TeacherBasicInfoUpdateDTO;
import com.iktpreobuka.schoolregister.entities.dto.TeacherDTO;
import com.iktpreobuka.schoolregister.entities.dto.UpdatePasswordDTO;
import com.iktpreobuka.schoolregister.repositories.AccountRepository;
import com.iktpreobuka.schoolregister.repositories.RoleRepository;
import com.iktpreobuka.schoolregister.repositories.TeacherRepository;
import com.iktpreobuka.schoolregister.services.AccountDao;
import com.iktpreobuka.schoolregister.services.FileHandler;
import com.iktpreobuka.schoolregister.services.UserDao;
import com.iktpreobuka.schoolregister.util.Encryption;
import com.iktpreobuka.schoolregister.util.RESTError;

@RestController
@RequestMapping(path = "/eregister/teacher")
public class TeacherController {
	
	@Autowired
	TeacherRepository teacherRepository;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private FileHandler fileHandler;


	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllActive() {
		try {
			Iterable<TeacherEntity> techers = teacherRepository.findAllActive();
			return new ResponseEntity<Iterable<TeacherEntity>>(techers, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/all")
	public ResponseEntity<?> getAll() {
		try {
			Iterable<TeacherEntity> techers = teacherRepository.findAll();
			return new ResponseEntity<Iterable<TeacherEntity>>(techers, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> findTeacherById(@PathVariable Integer id) {
		try {
			TeacherEntity teacher = teacherRepository.findById(id).orElse(null);

			if (teacher == null || userDao.getActiveAccountForTeacher(teacher).isEmpty())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return new ResponseEntity<TeacherEntity>(teacher, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addNewTeacher(@RequestBody TeacherDTO teacher) {

		try {

			if (teacher.getPassword() == null || teacher.getPassword() == " " || teacher.getPassword() == "") {
				return new ResponseEntity<RESTError>(new RESTError(2, "password not specified"),
						HttpStatus.BAD_REQUEST);
			}
			if (teacher.getUsername() == null || teacher.getUsername() == " " || teacher.getUsername() == "") {
				return new ResponseEntity<RESTError>(new RESTError(3, "username not specified"),
						HttpStatus.BAD_REQUEST);
			}

			if (teacher.getName() == null || teacher.getName() == " " || teacher.getName() == "") {
				return new ResponseEntity<RESTError>(new RESTError(4, "first name not specified"),
						HttpStatus.BAD_REQUEST);
			}

			if (teacher.getSurname() == null || teacher.getSurname() == " " || teacher.getSurname() == "") {
				return new ResponseEntity<RESTError>(new RESTError(5, "last name not specified"),
						HttpStatus.BAD_REQUEST);
			}

			if (teacher.getEmail() == null || teacher.getEmail() == " " || teacher.getEmail() == "") {
				return new ResponseEntity<RESTError>(new RESTError(6, "email not specified"), HttpStatus.BAD_REQUEST);
			}

			if (teacher.getDateOfBirth() == null) {
				return new ResponseEntity<RESTError>(new RESTError(7, "please specify date of birth"),
						HttpStatus.BAD_REQUEST);
			}

			if (teacher.getJmbg() == null || teacher.getJmbg() == " " || teacher.getJmbg() == "") {
				return new ResponseEntity<RESTError>(new RESTError(9, "JMBG not specified"), HttpStatus.BAD_REQUEST);
			}

			if (teacher.getPay() == null) {
				return new ResponseEntity<RESTError>(new RESTError(8, "pay not specified"), HttpStatus.BAD_REQUEST);
			}
			
			if (teacher.getTitle() == null || teacher.getTitle() == " " || teacher.getTitle() == "") {
				return new ResponseEntity<RESTError>(new RESTError(10, "title not specified"), HttpStatus.BAD_REQUEST);
			}



			if (accountDao.doesAccountExists(teacher.getUsername()))
				return new ResponseEntity<RESTError>(new RESTError(1, "account already exists"),
						HttpStatus.BAD_REQUEST);

			AccountEntity acc = new AccountEntity();
			acc.setPassword(Encryption.getPassEncoded(teacher.getPassword()));
			acc.setUsername(teacher.getUsername());
			acc.setRole(roleRepository.findById(2).orElse(null));
			acc.setActive(true);


			if (!userDao.findExistingUsers(teacher.getJmbg()).isEmpty()) {
				if (!userDao.getUsersTeacherAccount(userDao.findExistingUsers(teacher.getJmbg()).get(0)).isEmpty())
					return new ResponseEntity<RESTError>(new RESTError(11, "user has allready a teacher account"),
							HttpStatus.BAD_REQUEST);
				acc.setUser(userDao.findExistingUsers(teacher.getJmbg()).get(0));
				accountRepository.save(acc);
				teacherRepository.inserIntoTeacherTable(userDao.findExistingUsers(teacher.getJmbg()).get(0).getId(),
						teacher.getPay(), teacher.getTitle());
				return new ResponseEntity<String>("new account added to the existing user with id "
						+ userDao.findExistingUsers(teacher.getJmbg()).get(0).getId(), HttpStatus.CREATED);
			}
			TeacherEntity te = new TeacherEntity();
			te.setName(teacher.getName());
			te.setJmbg(teacher.getJmbg());
			te.setDateOfBirth(teacher.getDateOfBirth());
			te.setEmail(teacher.getEmail());
			te.setSurname(teacher.getSurname());
			te.setTitle(teacher.getTitle());
			te.setPay(teacher.getPay());			
			teacherRepository.save(te);
			acc.setUser(te);
			accountRepository.save(acc);

			return new ResponseEntity<TeacherEntity>(te, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> updateTeacherBasicInfo(@PathVariable Integer id,
			@RequestBody TeacherBasicInfoUpdateDTO teacher) {
		try {
			TeacherEntity te = teacherRepository.findById(id).orElse(null);
			if ((te == null) || (!userDao.getActiveAccountForTeacher(te).get(0).getActive())) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			if (teacher.getName() == null && teacher.getSurname() == null && teacher.getDateOfBirth() == null
					&& teacher.getEmail() == null && teacher.getJmbg() == null && teacher.getPay() == null
					&& teacher.getTitle() == null )
				return new ResponseEntity<RESTError>(new RESTError(12, "attributes to be modified not specified"),
						HttpStatus.BAD_REQUEST);
			te = userDao.checkPropToBeChangedTeacher(te, teacher);
			
			teacherRepository.save(te);
			return new ResponseEntity<TeacherEntity>(te, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteTeacherById(@PathVariable Integer id) {
		try {
			TeacherEntity teacher = teacherRepository.findById(id).orElse(null);
			if ((teacher == null) || (!userDao.getActiveAccountForTeacher(teacher).get(0).getActive())) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			userDao.getActiveAccountForTeacher(teacher).get(0).setActive(false);
			teacherRepository.save(teacher);
			return new ResponseEntity<TeacherEntity>(teacher, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/updatePassword")
	@Secured("ROLE_TEACHER")
	public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordDTO utdpsw) {
		try {
			AccountEntity acc = accountRepository.findByUsername(accountDao.getLoggedInUsername());
			if (acc.getRole().getId() != 2)
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			if (!Encryption.comparePassword(utdpsw.getOldPassword(), acc.getPassword()))
				return new ResponseEntity<RESTError>(new RESTError(98, "wrong password"), HttpStatus.BAD_REQUEST);
			if (!utdpsw.getNewPassword().equals(utdpsw.getNewPasswordConf()))
				return new ResponseEntity<RESTError>(new RESTError(99, "new password not matching"),
						HttpStatus.BAD_REQUEST);
			acc.setPassword(Encryption.getPassEncoded(utdpsw.getNewPassword()));
			accountRepository.save(acc);
			return new ResponseEntity<TeacherEntity>(teacherRepository.findById(acc.getUser().getId()).orElse(null),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/uploadImage/{id}")
	public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file, @PathVariable("id") Integer id) {

		try {
			TeacherEntity teacher = teacherRepository.findById(id).orElse(null);
			if (teacher == null || !userDao.getActiveAccountForTeacher(teacher).get(0).getActive())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			String result = null;
			try {
				result = fileHandler.singleFileUpload(file);
			} catch (IOException e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			teacher.setPictureURL(result);
			teacherRepository.save(teacher);
			return new ResponseEntity<TeacherEntity>(teacher, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	

}
