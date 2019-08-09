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

import com.iktpreobuka.schoolregister.entities.GradeEntity;
import com.iktpreobuka.schoolregister.repositories.GradeRepository;
import com.iktpreobuka.schoolregister.util.RESTError;

@RestController
@RequestMapping(path = "/eregister/grade")
@Secured("ROLE_ADMIN")
public class GradeController {
	
	@Autowired
	private GradeRepository gradeRepository;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addNewGrade(@RequestBody GradeEntity newGrade) {
		try {
			if (newGrade.getName() == null || newGrade.getName() == " ") {
				return new ResponseEntity<RESTError>(new RESTError(1, "cannot create new grade with no name specified"),
						HttpStatus.BAD_REQUEST);
			}

			GradeEntity grade = new GradeEntity();
			grade.setName(newGrade.getName());
			
			List<GradeEntity> grades = gradeRepository.findByName(newGrade.getName());
			
			if(!grades.isEmpty())
				return new ResponseEntity<RESTError>(new RESTError(3, "grade allready exists"),
						HttpStatus.BAD_REQUEST);

			gradeRepository.save(grade);
			return new ResponseEntity<GradeEntity>(grade, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> findGradeById(@PathVariable Integer id) {
		try {
			GradeEntity grade = gradeRepository.findById(id).orElse(null);

			if (grade == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return new ResponseEntity<GradeEntity>(grade, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public ResponseEntity<?> getAllGrades() {
		return new ResponseEntity<List<GradeEntity>>((List<GradeEntity>) gradeRepository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> updateGrade(@PathVariable Integer id, @RequestBody GradeEntity updateGrade) {
		try {
			GradeEntity grade = gradeRepository.findById(id).orElse(null);

			if (grade == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			if (updateGrade == null) {
				return new ResponseEntity<RESTError>(
						new RESTError(2, "at least one parameter for change needs to be specified"),
						HttpStatus.BAD_REQUEST);
			}

			if (updateGrade.getName() != null || !updateGrade.getName().equals(" ") || !updateGrade.getName().equals("")) {
				grade.setName(updateGrade.getName());
			}

			gradeRepository.save(grade);
			return new ResponseEntity<GradeEntity>(grade, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteGrade(@PathVariable Integer id) {
		try {
			GradeEntity grade = gradeRepository.findById(id).orElse(null);

			if (grade == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			gradeRepository.delete(grade);

			return new ResponseEntity<GradeEntity>(grade, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

}
