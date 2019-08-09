package com.iktpreobuka.schoolregister.controllers;

import java.time.Year;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.schoolregister.entities.GradeEntity;
import com.iktpreobuka.schoolregister.entities.GroupEntity;
import com.iktpreobuka.schoolregister.entities.SchoolEntity;
import com.iktpreobuka.schoolregister.entities.TeacherEntity;
import com.iktpreobuka.schoolregister.entities.dto.GroupDTO;
import com.iktpreobuka.schoolregister.repositories.GradeRepository;
import com.iktpreobuka.schoolregister.repositories.GroupRepository;
import com.iktpreobuka.schoolregister.repositories.SchoolRepository;
import com.iktpreobuka.schoolregister.repositories.TeacherRepository;
import com.iktpreobuka.schoolregister.services.GroupDao;
import com.iktpreobuka.schoolregister.services.UserDao;
import com.iktpreobuka.schoolregister.util.RESTError;

@RestController
@RequestMapping(path = "/eregister/group")
public class GroupController {

	@Autowired
	private GradeRepository gradeRepository;

	@Autowired
	private SchoolRepository schoolRepository;

	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private GroupDao groupDao;

	@RequestMapping(method = RequestMethod.POST, value = "/grade/{gradeId}/school/{schoolId}")
	public ResponseEntity<?> addSubjectGrade(@PathVariable("gradeId") Integer gradeId,
			@PathVariable("schoolId") Integer schoolId, @RequestBody GroupDTO groupYear) {

		try {
			GradeEntity grade = gradeRepository.findById(gradeId).orElse(null);
			if (grade == null)
				return new ResponseEntity<RESTError>(new RESTError(3, "grade not found"), HttpStatus.NOT_FOUND);
			SchoolEntity school = schoolRepository.findById(schoolId).orElse(null);
			if (school == null || school.getActive() == false)
				return new ResponseEntity<RESTError>(new RESTError(2, "school not found"), HttpStatus.NOT_FOUND);

			GroupEntity gr = new GroupEntity();
			gr.setActive(true);
			gr.setGenerationYear(Year.parse(groupYear.getGenerationYear()));
			gr.setGroupGrade(grade);
			gr.setGroupSchool(school);

			groupRepository.save(gr);

			return new ResponseEntity<GroupEntity>(gr, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{groupId}/teacher/{teacherId}")
	public ResponseEntity<?> addHomeroomTeacher(@PathVariable("groupId") Integer groupId,
			@PathVariable("teacherId") Integer teacherId) {

		try {
			GroupEntity gr = groupRepository.findById(groupId).orElse(null);
			if (gr == null || !gr.getActive())
				return new ResponseEntity<RESTError>(new RESTError(1, "group not found"), HttpStatus.NOT_FOUND);
			TeacherEntity te = teacherRepository.findById(teacherId).orElse(null);
			if (te == null || userDao.getActiveAccountForTeacher(te).isEmpty())
				return new ResponseEntity<RESTError>(new RESTError(4, "teacher not found"), HttpStatus.NOT_FOUND);

			gr.setHomeRoomTeacher(te);
			groupRepository.save(gr);

			return new ResponseEntity<GroupEntity>(gr, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
	
	}
	

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> findByID(@PathVariable Integer id) {
		try {
			GroupEntity gr = groupRepository.findById(id).orElse(null);
			if (gr == null || !gr.getActive())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return new ResponseEntity<GroupEntity>(gr, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public ResponseEntity<?> getAllActiveGroups() {
		return new ResponseEntity<List<GroupEntity>>((List<GroupEntity>) groupRepository.findAllActiveGroups(),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/all")
	public ResponseEntity<?> getAllSubjects() {
		return new ResponseEntity<List<GroupEntity>>((List<GroupEntity>) groupRepository.findAll(),
				HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteSubjectById(@PathVariable Integer id) {
		try {
			GroupEntity gr = groupRepository.findById(id).orElse(null);
			if (gr == null || !gr.getActive())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			gr.setActive(false);
			groupRepository.save(gr);
			return new ResponseEntity<GroupEntity>(gr, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/increaseGrade/{groupId}")
	public ResponseEntity<?> increaseGrade(@PathVariable("groupId") Integer groupId) {

		try {
			GroupEntity gr = groupRepository.findById(groupId).orElse(null);
			if (gr == null || !gr.getActive())
				return new ResponseEntity<RESTError>(new RESTError(1, "group not found"), HttpStatus.NOT_FOUND);
			Integer temp = groupDao.getIncreasesGrade(gr);
			if(temp==9) {
				gr.setActive(false);
				groupRepository.save(gr);
				return new ResponseEntity<RESTError>(new RESTError(50, "group finished school"), HttpStatus.OK);
			}
			
			gr.setGroupGrade(gradeRepository.findById(temp).orElse(null));
			groupRepository.save(gr);

			return new ResponseEntity<GroupEntity>(gr, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
	
	}
	
}
