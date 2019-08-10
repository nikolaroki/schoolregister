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
import com.iktpreobuka.schoolregister.entities.GradeSubject;
import com.iktpreobuka.schoolregister.entities.SubjectEntity;
import com.iktpreobuka.schoolregister.entities.dto.SubjectDTO;
import com.iktpreobuka.schoolregister.repositories.GradeRepository;
import com.iktpreobuka.schoolregister.repositories.GradeSubjectRepository;
import com.iktpreobuka.schoolregister.repositories.SubjectRepository;
import com.iktpreobuka.schoolregister.services.SubjectDao;
import com.iktpreobuka.schoolregister.util.RESTError;

@RestController
@RequestMapping(path = "/eregister/subject")
@Secured("ROLE_ADMIN")
public class SubjectController {

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private SubjectDao subjectDao;

	@Autowired
	private GradeRepository gradeRepository;

	@Autowired
	private GradeSubjectRepository gradeSubjectRepository;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addNewSubject(@RequestBody SubjectDTO subj) {

		try {

			if (subj.getName() == null || subj.getName() == " " || subj.getName() == "") {
				return new ResponseEntity<RESTError>(new RESTError(2, "subject number not specified"),
						HttpStatus.BAD_REQUEST);
			}
			if (subj.getClassesPerWeek() == null || subj.getClassesPerWeek() == 0) {
				return new ResponseEntity<RESTError>(new RESTError(3, "classes per week not specified"),
						HttpStatus.BAD_REQUEST);
			}

			List<SubjectEntity> subjects = subjectRepository.findByName(subj.getName());

			if (!subjects.isEmpty()) {
				SubjectEntity subject = subjects.get(0);
				if (!subject.getActive()) {
					subject.setActive(true);
					subjectRepository.save(subject);
					return new ResponseEntity<SubjectEntity>(subject, HttpStatus.CREATED);
				}
				return new ResponseEntity<RESTError>(new RESTError(1, "subject allready existis"),
						HttpStatus.BAD_REQUEST);
			}

			SubjectEntity subject = new SubjectEntity();
			subject.setClassesPerWeek(subj.getClassesPerWeek());
			subject.setName(subj.getName());
			subject.setActive(true);
			subjectRepository.save(subject);
			return new ResponseEntity<SubjectEntity>(subject, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> findBySubjectId(@PathVariable Integer id) {
		try {
			SubjectEntity subj = subjectRepository.findById(id).orElse(null);

			if (subj == null || subj.getActive() == false)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return new ResponseEntity<SubjectEntity>(subj, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public ResponseEntity<?> getAllActiveSubjects() {
		return new ResponseEntity<List<SubjectEntity>>((List<SubjectEntity>) subjectRepository.findAllActiveSubjects(),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/all")
	public ResponseEntity<?> getAllSubjects() {
		return new ResponseEntity<List<SubjectEntity>>((List<SubjectEntity>) subjectRepository.findAll(),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> changeSubject(@PathVariable Integer id, @RequestBody SubjectDTO updateSubj) {
		try {
			SubjectEntity subj = subjectRepository.findById(id).orElse(null);
			if ((subj == null) || (subj.getActive() == false)) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			if (updateSubj.getName() == null && updateSubj.getClass() == null)
				return new ResponseEntity<RESTError>(new RESTError(4, "atributes to be modified not specified"),
						HttpStatus.BAD_REQUEST);

			SubjectEntity tempSubj = new SubjectEntity();
			tempSubj = subjectDao.copySubject(subj, tempSubj);
			tempSubj = subjectDao.checkPropToBeChanged(tempSubj, updateSubj);
			if (!subjectRepository.findByNameAndClassesPerWeek(tempSubj.getName(), tempSubj.getClassesPerWeek())
					.isEmpty()) {
				tempSubj = subjectRepository.findByName(tempSubj.getName()).get(0);
				if (!tempSubj.getActive()) {
					tempSubj.setActive(true);
					subj.setActive(false);
					subjectRepository.save(tempSubj);
					subjectRepository.save(subj);
					return new ResponseEntity<SubjectEntity>(tempSubj, HttpStatus.OK);
				}
				return new ResponseEntity<RESTError>(new RESTError(1, "subject allready existis"),
						HttpStatus.BAD_REQUEST);
			}

			subj = subjectDao.checkPropToBeChanged(subj, updateSubj);
			subjectRepository.save(subj);
			return new ResponseEntity<SubjectEntity>(subj, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteSubjectById(@PathVariable Integer id) {
		try {
			SubjectEntity subj = subjectRepository.findById(id).orElse(null);
			if (subj == null || subj.getActive() == false)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			subj.setActive(false);
			subjectRepository.save(subj);
			return new ResponseEntity<SubjectEntity>(subj, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{subjectId}/addGrade/{gradeId}")
	public ResponseEntity<?> addSubjectGrade(@PathVariable("subjectId") Integer subjectId,
			@PathVariable("gradeId") Integer gradeId) {

		try {
			SubjectEntity subj = subjectRepository.findById(subjectId).orElse(null);
			if (subj == null || subj.getActive() == false)
				return new ResponseEntity<RESTError>(new RESTError(11, "subject not found"), HttpStatus.NOT_FOUND);
			GradeEntity grade = gradeRepository.findById(gradeId).orElse(null);
			if (grade == null)
				return new ResponseEntity<RESTError>(new RESTError(12, "grade not found"), HttpStatus.NOT_FOUND);

			List<GradeSubject> grsub = gradeSubjectRepository.findBySubjectAndGrade(subj, grade);

			if (!grsub.isEmpty())
				return new ResponseEntity<RESTError>(new RESTError(13, "entry allready exist"), HttpStatus.BAD_REQUEST);

			GradeSubject gs = new GradeSubject();
			gs.setGrade(grade);
			gs.setSubject(subj);
			gradeSubjectRepository.save(gs);
			return new ResponseEntity<GradeSubject>(gs, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

}
