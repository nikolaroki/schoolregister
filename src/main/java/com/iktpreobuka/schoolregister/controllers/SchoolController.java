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

import com.iktpreobuka.schoolregister.entities.AddressEntity;
import com.iktpreobuka.schoolregister.entities.SchoolEntity;
import com.iktpreobuka.schoolregister.entities.dto.SchoolDTO;
import com.iktpreobuka.schoolregister.repositories.AddressRepository;
import com.iktpreobuka.schoolregister.repositories.SchoolRepository;
import com.iktpreobuka.schoolregister.services.AddressDao;
import com.iktpreobuka.schoolregister.services.SchoolDao;
import com.iktpreobuka.schoolregister.util.RESTError;

@RestController
@RequestMapping(path = "/eregister/school")
public class SchoolController {

	@Autowired
	private SchoolRepository schoolRepository;

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private SchoolDao schoolDao;

	@RequestMapping(method = RequestMethod.GET)
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> getAll() {
		try {
			Iterable<SchoolEntity> schools = schoolRepository.findAll();
			return new ResponseEntity<Iterable<SchoolEntity>>(schools, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/active")
	public ResponseEntity<?> getAllActive() {
		try {
			Iterable<SchoolEntity> schools = schoolRepository.findAllActive();
			return new ResponseEntity<Iterable<SchoolEntity>>(schools, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable Integer id) {
		try {
			SchoolEntity school = schoolRepository.findById(id).orElse(null);

			if (school == null || !school.getActive())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return new ResponseEntity<SchoolEntity>(school, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addNewSchool(@RequestBody SchoolDTO school) {

		try {
			if (school.getStreetNumber() == null || school.getStreetNumber() == " " || school.getStreetNumber() == "") {
				return new ResponseEntity<RESTError>(new RESTError(2, "street number not specified"),
						HttpStatus.BAD_REQUEST);
			}
			if (school.getStreet() == null || school.getStreet() == " " || school.getStreet() == "") {
				return new ResponseEntity<RESTError>(new RESTError(3, "street not specified"), HttpStatus.BAD_REQUEST);
			}
			if (school.getCity() == null || school.getCity() == " " || school.getCity() == "") {
				return new ResponseEntity<RESTError>(new RESTError(1, "city not specified"), HttpStatus.BAD_REQUEST);
			}

			if (school.getSchoolName() == null || school.getSchoolName() == " " || school.getSchoolName() == "") {
				return new ResponseEntity<RESTError>(new RESTError(4, "school name not specified"),
						HttpStatus.BAD_REQUEST);
			}

			AddressEntity adr = new AddressEntity();
			adr.setCity(school.getCity());
			adr.setStreet(school.getStreet());
			adr.setStreetNumber(school.getStreetNumber());
			adr.setActive(true);

			if (!addressDao.findIfExists(adr).isEmpty())
				adr = addressDao.findIfExists(adr).get(0);
			if (!adr.getActive())
				adr.setActive(true);
			addressRepository.save(adr);
			List<SchoolEntity> schools = schoolRepository.findBySchoolNameAndSchoolAddress(school.getSchoolName(), adr);

			if (!schools.isEmpty())
				return new ResponseEntity<RESTError>(new RESTError(5, "school allready exists in system"),
						HttpStatus.BAD_REQUEST);

			SchoolEntity sc = new SchoolEntity();
			sc.setSchoolName(school.getSchoolName());
			sc.setSchoolAddress(adr);
			sc.setActive(true);
			schoolRepository.save(sc);

			return new ResponseEntity<SchoolEntity>(sc, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> updateSchool(@PathVariable Integer id,
			@RequestBody SchoolDTO school) {
		try {
			SchoolEntity sc = schoolRepository.findById(id).orElse(null);
			if ((sc == null) || !sc.getActive()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			if (school.getSchoolName() == null	&& school.getStreetNumber() == null && school.getStreet() == null && school.getCity() == null)
				return new ResponseEntity<RESTError>(new RESTError(6, "attributes to be modified not specified"),
						HttpStatus.BAD_REQUEST);
			sc = schoolDao.checkPropToBeChanged(sc, school);
			addressRepository.save(sc.getSchoolAddress());
			schoolRepository.save(sc);
			return new ResponseEntity<SchoolEntity>(sc, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteSchoolById(@PathVariable Integer id) {
		try {
			SchoolEntity sc = schoolRepository.findById(id).orElse(null);
			if (sc == null || !sc.getActive())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			sc.setActive(false);
			schoolRepository.save(sc);
			return new ResponseEntity<SchoolEntity>(sc, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
