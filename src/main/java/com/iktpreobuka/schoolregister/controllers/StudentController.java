package com.iktpreobuka.schoolregister.controllers;

import java.io.IOException;
import java.util.List;

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
import com.iktpreobuka.schoolregister.entities.AddressEntity;
import com.iktpreobuka.schoolregister.entities.ParentEntity;
import com.iktpreobuka.schoolregister.entities.StudentEntity;
import com.iktpreobuka.schoolregister.entities.StudentParent;
import com.iktpreobuka.schoolregister.entities.dto.StudentBasicInfoUpdateDTPO;
import com.iktpreobuka.schoolregister.entities.dto.StudentDTO;
import com.iktpreobuka.schoolregister.entities.dto.UpdatePasswordDTO;
import com.iktpreobuka.schoolregister.repositories.AccountRepository;
import com.iktpreobuka.schoolregister.repositories.AddressRepository;
import com.iktpreobuka.schoolregister.repositories.ChildParentRepository;
import com.iktpreobuka.schoolregister.repositories.ParentRepository;
import com.iktpreobuka.schoolregister.repositories.RoleRepository;
import com.iktpreobuka.schoolregister.repositories.StudentRepository;
import com.iktpreobuka.schoolregister.services.AccountDao;
import com.iktpreobuka.schoolregister.services.AddressDao;
import com.iktpreobuka.schoolregister.services.FileHandler;
import com.iktpreobuka.schoolregister.services.UserDao;
import com.iktpreobuka.schoolregister.util.Encryption;
import com.iktpreobuka.schoolregister.util.RESTError;

@RestController
@RequestMapping(path = "/eregister/student")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;

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
	private FileHandler fileHandler;

	@Autowired
	private ParentRepository parentRepository;

	@Autowired
	private ChildParentRepository childParentRepository;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllActive() {
		try {
			Iterable<StudentEntity> students = studentRepository.findAllActive();
			return new ResponseEntity<Iterable<StudentEntity>>(students, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/all")
	public ResponseEntity<?> getAll() {
		try {
			Iterable<StudentEntity> students = studentRepository.findAll();
			return new ResponseEntity<Iterable<StudentEntity>>(students, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> findStudentById(@PathVariable Integer id) {
		try {
			StudentEntity student = studentRepository.findById(id).orElse(null);

			if (student == null || userDao.getActiveAccountForStudent(student).isEmpty())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return new ResponseEntity<StudentEntity>(student, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addNewStudent(@RequestBody StudentDTO student) {

		try {

			if (student.getPassword() == null || student.getPassword() == " " || student.getPassword() == "") {
				return new ResponseEntity<RESTError>(new RESTError(2, "password not specified"),
						HttpStatus.BAD_REQUEST);
			}
			if (student.getUsername() == null || student.getUsername() == " " || student.getUsername() == "") {
				return new ResponseEntity<RESTError>(new RESTError(3, "username not specified"),
						HttpStatus.BAD_REQUEST);
			}

			if (student.getName() == null || student.getName() == " " || student.getName() == "") {
				return new ResponseEntity<RESTError>(new RESTError(4, "first name not specified"),
						HttpStatus.BAD_REQUEST);
			}

			if (student.getSurname() == null || student.getSurname() == " " || student.getSurname() == "") {
				return new ResponseEntity<RESTError>(new RESTError(5, "last name not specified"),
						HttpStatus.BAD_REQUEST);
			}

			if (student.getEmail() == null || student.getEmail() == " " || student.getEmail() == "") {
				return new ResponseEntity<RESTError>(new RESTError(6, "email not specified"), HttpStatus.BAD_REQUEST);
			}

			if (student.getDateOfBirth() == null) {
				return new ResponseEntity<RESTError>(new RESTError(7, "please specify date of birth"),
						HttpStatus.BAD_REQUEST);
			}

			if (student.getJmbg() == null || student.getJmbg() == " " || student.getJmbg() == "") {
				return new ResponseEntity<RESTError>(new RESTError(9, "JMBG not specified"), HttpStatus.BAD_REQUEST);
			}

			if (student.getStreetNumber() == null || student.getStreetNumber() == " "
					|| student.getStreetNumber() == "") {
				return new ResponseEntity<RESTError>(new RESTError(11, "street number not specified"),
						HttpStatus.BAD_REQUEST);
			}

			if (student.getStreet() == null || student.getStreet() == " " || student.getStreet() == "") {
				return new ResponseEntity<RESTError>(new RESTError(12, "street not specified"), HttpStatus.BAD_REQUEST);
			}

			if (student.getCity() == null || student.getCity() == " " || student.getCity() == "") {
				return new ResponseEntity<RESTError>(new RESTError(13, "city not specified"), HttpStatus.BAD_REQUEST);
			}

			if (student.getGender() == null) {
				return new ResponseEntity<RESTError>(new RESTError(8, "gender not specified"), HttpStatus.BAD_REQUEST);
			}

			if (accountDao.doesAccountExists(student.getUsername()))
				return new ResponseEntity<RESTError>(new RESTError(1, "account already exists"),
						HttpStatus.BAD_REQUEST);

			AccountEntity acc = new AccountEntity();
			acc.setPassword(Encryption.getPassEncoded(student.getPassword()));
			acc.setUsername(student.getUsername());
			acc.setRole(roleRepository.findById(3).orElse(null));
			acc.setActive(true);

			AddressEntity adr = new AddressEntity();
			adr.setCity(student.getCity());
			adr.setStreet(student.getStreet());
			adr.setStreetNumber(student.getStreetNumber());
			adr.setActive(true);

			if (!addressDao.findIfExists(adr).isEmpty())
				adr = addressDao.findIfExists(adr).get(0);
			if (!adr.getActive())
				adr.setActive(true);

			if (!userDao.findExistingUsers(student.getJmbg()).isEmpty()) {
				if (!userDao.getUsersStudentAccount(userDao.findExistingUsers(student.getJmbg()).get(0)).isEmpty())
					return new ResponseEntity<RESTError>(new RESTError(14, "user has allready an student account"),
							HttpStatus.BAD_REQUEST);
				acc.setUser(userDao.findExistingUsers(student.getJmbg()).get(0));
				accountRepository.save(acc);
				addressRepository.save(adr);
				studentRepository.inserIntoStudentTable(userDao.findExistingUsers(student.getJmbg()).get(0).getId(),
						adr.getId(), student.getGender().ordinal());
				return new ResponseEntity<String>("new account added to the existing user with id "
						+ userDao.findExistingUsers(student.getJmbg()).get(0).getId(), HttpStatus.CREATED);
			}
			StudentEntity stud = new StudentEntity();
			stud.setName(student.getName());
			stud.setJmbg(student.getJmbg());
			stud.setDateOfBirth(student.getDateOfBirth());
			stud.setEmail(student.getEmail());
			stud.setSurname(student.getSurname());
			stud.setGender(student.getGender());
			addressRepository.save(adr);
			stud.setStudentAddress(adr);
			;
			studentRepository.save(stud);
			acc.setUser(stud);
			accountRepository.save(acc);

			return new ResponseEntity<StudentEntity>(stud, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> updateStudentBasicInfo(@PathVariable Integer id,
			@RequestBody StudentBasicInfoUpdateDTPO student) {
		try {
			StudentEntity stud = studentRepository.findById(id).orElse(null);
			if ((stud == null) || (!userDao.getActiveAccountForStudent(stud).get(0).getActive())) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			if (student.getName() == null && student.getSurname() == null && student.getDateOfBirth() == null
					&& student.getEmail() == null && student.getJmbg() == null && student.getGender() == null
					&& student.getStreetNumber() == null && student.getStreet() == null && student.getCity() == null)
				return new ResponseEntity<RESTError>(new RESTError(15, "attributes to be modified not specified"),
						HttpStatus.BAD_REQUEST);
			stud = userDao.checkPropToBeChangedStudent(stud, student);
			addressRepository.save(stud.getStudentAddress());
			studentRepository.save(stud);
			return new ResponseEntity<StudentEntity>(stud, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteStudentById(@PathVariable Integer id) {
		try {
			StudentEntity stud = studentRepository.findById(id).orElse(null);
			if ((stud == null) || (!userDao.getActiveAccountForStudent(stud).get(0).getActive())) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			userDao.getActiveAccountForStudent(stud).get(0).setActive(false);
			studentRepository.save(stud);
			return new ResponseEntity<StudentEntity>(stud, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/updatePassword")
	@Secured("ROLE_STUDENT")
	public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordDTO utdpsw) {
		try {
			AccountEntity acc = accountRepository.findByUsername(accountDao.getLoggedInUsername());
			if (acc.getRole().getId() != 3)
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			if (!Encryption.comparePassword(utdpsw.getOldPassword(), acc.getPassword()))
				return new ResponseEntity<RESTError>(new RESTError(98, "wrong password"), HttpStatus.BAD_REQUEST);
			if (!utdpsw.getNewPassword().equals(utdpsw.getNewPasswordConf()))
				return new ResponseEntity<RESTError>(new RESTError(99, "new password not matching"),
						HttpStatus.BAD_REQUEST);
			acc.setPassword(Encryption.getPassEncoded(utdpsw.getNewPassword()));
			accountRepository.save(acc);
			return new ResponseEntity<StudentEntity>(studentRepository.findById(acc.getUser().getId()).orElse(null),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/uploadImage/{id}")
	public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file, @PathVariable("id") Integer id) {

		try {
			StudentEntity student = studentRepository.findById(id).orElse(null);
			if (student == null || !userDao.getActiveAccountForStudent(student).get(0).getActive())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			String result = null;
			try {
				result = fileHandler.singleFileUpload(file);
			} catch (IOException e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			student.setPictureUrl(result);
			studentRepository.save(student);
			return new ResponseEntity<StudentEntity>(student, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(method = RequestMethod.POST, value = "/addParent/{parentId}/toChild/{childId}")
	public ResponseEntity<?> addFamilyRelationship(@PathVariable("parentId") Integer parentId,
			@PathVariable("childId") Integer childId) {
		try {
			StudentEntity student = studentRepository.findById(childId).orElse(null);
			if (student == null || !userDao.getActiveAccountForStudent(student).get(0).getActive())
				return new ResponseEntity<RESTError>(new RESTError(16, "student not found"), HttpStatus.NOT_FOUND);
			ParentEntity parent = parentRepository.findById(parentId).orElse(null);
			if (parent == null || !userDao.getActiveAccountForParent(parent).get(0).getActive())
				return new ResponseEntity<RESTError>(new RESTError(17, "parent not found"), HttpStatus.NOT_FOUND);

			List<StudentParent> cp = childParentRepository.findByChildAndParent(student, parent);
			if (!cp.isEmpty())
				return new ResponseEntity<RESTError>(new RESTError(18, "entry already in DB"), HttpStatus.NOT_FOUND);

			StudentParent childparent = new StudentParent();
			childparent.setChild(student);
			childparent.setParent(parent);

			childParentRepository.save(childparent);

			return new ResponseEntity<StudentParent>(childparent, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/children/{id}")
	public ResponseEntity<?> addChildrenFromParent(@PathVariable("id") Integer id) {
		try {
			ParentEntity parent = parentRepository.findById(id).orElse(null);
			if (parent == null || userDao.getActiveAccountForParent(parent).isEmpty())
				return new ResponseEntity<RESTError>(new RESTError(17, "parent not found"), HttpStatus.NOT_FOUND);
			List<StudentEntity> students = childParentRepository.findChildrenByParent(parent);
			if(students.isEmpty())
				return new ResponseEntity<RESTError>(new RESTError(19, "parent has no children"), HttpStatus.NOT_FOUND);
			return new ResponseEntity<List<StudentEntity>>(students, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

}
