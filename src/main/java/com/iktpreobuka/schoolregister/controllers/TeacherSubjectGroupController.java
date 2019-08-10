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

import com.iktpreobuka.schoolregister.entities.GroupEntity;
import com.iktpreobuka.schoolregister.entities.SubjectEntity;
import com.iktpreobuka.schoolregister.entities.TeacherEntity;
import com.iktpreobuka.schoolregister.entities.TeacherSubjectGroup;
import com.iktpreobuka.schoolregister.repositories.GroupRepository;
import com.iktpreobuka.schoolregister.repositories.SubjectRepository;
import com.iktpreobuka.schoolregister.repositories.TeacherRepository;
import com.iktpreobuka.schoolregister.repositories.TeacherSubjectGroupRepository;
import com.iktpreobuka.schoolregister.services.ScheduleDao;
import com.iktpreobuka.schoolregister.services.UserDao;
import com.iktpreobuka.schoolregister.util.RESTError;

@RestController
@RequestMapping(path = "/eregister/schedule")
public class TeacherSubjectGroupController {

/*	@Autowired
	private AccountDao accountDao;

	@Autowired
	private AccountRepository accountRepository;*/

	@Autowired
	private UserDao userDao;

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private TeacherSubjectGroupRepository teacherSubjectGroupRepository;

	@Autowired
	private ScheduleDao scheduleDao;

	@RequestMapping(method = RequestMethod.POST, value = "/teacher/{teacherId}/subject/{subjectId}/group/{groupId}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> addSchedule(@PathVariable("teacherId") Integer teacherId,@PathVariable("subjectId") Integer subjectId,
			@PathVariable("groupId") Integer groupId) {

		try {
			TeacherEntity teacher = teacherRepository.findById(teacherId).orElse(null);
			if (teacher == null || userDao.getActiveAccountForTeacher(teacher).isEmpty())
				return new ResponseEntity<RESTError>(new RESTError(5, "teacher not found"), HttpStatus.NOT_FOUND);

			SubjectEntity subject = subjectRepository.findById(subjectId).orElse(null);
			if (subject == null || subject.getActive() == false)
				return new ResponseEntity<RESTError>(new RESTError(1, "subject not found"), HttpStatus.NOT_FOUND);

			GroupEntity group = groupRepository.findById(groupId).orElse(null);
			if (group == null || group.getActive() == false)
				return new ResponseEntity<RESTError>(new RESTError(2, "group not found"), HttpStatus.NOT_FOUND);

			if (!teacherSubjectGroupRepository.findByTeacherAndSubjectAndSchoolGroup(teacher, subject, group).isEmpty())
				return new ResponseEntity<RESTError>(new RESTError(3, "entry exists in db"), HttpStatus.BAD_REQUEST);

			if (scheduleDao.checkIfOnSameGrade(subject, group).isEmpty())
				return new ResponseEntity<RESTError>(
						new RESTError(4, "subject is not teached on the grade where this group is"),
						HttpStatus.BAD_REQUEST);

			TeacherSubjectGroup schedule = new TeacherSubjectGroup();

			schedule.setActive(true);
			schedule.setTeacher(teacher);
			schedule.setSchoolGroup(group);
			schedule.setSubject(subject);

			teacherSubjectGroupRepository.save(schedule);

			return new ResponseEntity<TeacherSubjectGroup>(schedule, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> findByID(@PathVariable Integer id) {
		try {
			TeacherSubjectGroup tsg = teacherSubjectGroupRepository.findById(id).orElse(null);
			if (tsg == null || !tsg.getActive())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return new ResponseEntity<TeacherSubjectGroup>(tsg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public ResponseEntity<?> getAllActiveSchedules() {
		return new ResponseEntity<List<TeacherSubjectGroup>>(
				(List<TeacherSubjectGroup>) teacherSubjectGroupRepository.findAllActiveSchedules(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/all")
	public ResponseEntity<?> getAllSchedules() {
		return new ResponseEntity<List<TeacherSubjectGroup>>(
				(List<TeacherSubjectGroup>) teacherSubjectGroupRepository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteScheduleById(@PathVariable Integer id) {
		try {
			TeacherSubjectGroup tsg = teacherSubjectGroupRepository.findById(id).orElse(null);
			if (tsg == null || !tsg.getActive())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			tsg.setActive(false);
			teacherSubjectGroupRepository.save(tsg);
			return new ResponseEntity<TeacherSubjectGroup>(tsg, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/teacher/{id}")
	public ResponseEntity<?> getByTeacher(@PathVariable Integer id) {
		try {
			TeacherEntity teacher = teacherRepository.findById(id).orElse(null);
			if (teacher == null || userDao.getActiveAccountForTeacher(teacher).isEmpty())
				return new ResponseEntity<RESTError>(new RESTError(5, "teacher not found"), HttpStatus.NOT_FOUND);
			List<TeacherSubjectGroup> tsgList = teacherSubjectGroupRepository.findByTeacher(teacher);
			if (tsgList.isEmpty())
				return new ResponseEntity<RESTError>(new RESTError(6, "no entry found"), HttpStatus.NOT_FOUND);

			return new ResponseEntity<List<TeacherSubjectGroup>>(tsgList, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/subject/{id}")
	public ResponseEntity<?> getBySubject(@PathVariable Integer id) {
		try {
			SubjectEntity subject = subjectRepository.findById(id).orElse(null);
			if (subject == null || !subject.getActive())
				return new ResponseEntity<RESTError>(new RESTError(1, "subject not found"), HttpStatus.NOT_FOUND);
			List<TeacherSubjectGroup> tsgList = teacherSubjectGroupRepository.findBySubject(subject);
			if (tsgList.isEmpty())
				return new ResponseEntity<RESTError>(new RESTError(6, "no entry found"), HttpStatus.NOT_FOUND);
			
			return new ResponseEntity<List<TeacherSubjectGroup>>(tsgList, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/group/{id}")
	public ResponseEntity<?> getByGroup(@PathVariable Integer id) {
		try {
			GroupEntity gr = groupRepository.findById(id).orElse(null);
			if (gr == null || !gr.getActive())
				return new ResponseEntity<RESTError>(new RESTError(2, "group not found"), HttpStatus.NOT_FOUND);
			List<TeacherSubjectGroup> tsgList = teacherSubjectGroupRepository.findBySchoolGroup(gr);
			if (tsgList.isEmpty())
				return new ResponseEntity<RESTError>(new RESTError(6, "no entry found"), HttpStatus.NOT_FOUND);
			
			return new ResponseEntity<List<TeacherSubjectGroup>>(tsgList, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
