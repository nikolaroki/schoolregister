package com.iktpreobuka.schoolregister.controllers;

import java.util.Date;
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

import com.iktpreobuka.schoolregister.entities.ParentEntity;
import com.iktpreobuka.schoolregister.entities.RegisterEntity;
import com.iktpreobuka.schoolregister.entities.StudentEntity;
import com.iktpreobuka.schoolregister.entities.SubjectEntity;
import com.iktpreobuka.schoolregister.entities.TeacherEntity;
import com.iktpreobuka.schoolregister.entities.TeacherSubjectGroup;
import com.iktpreobuka.schoolregister.entities.dto.MarkDTO;
import com.iktpreobuka.schoolregister.repositories.AccountRepository;
import com.iktpreobuka.schoolregister.repositories.ChildParentRepository;
import com.iktpreobuka.schoolregister.repositories.GroupRepository;
import com.iktpreobuka.schoolregister.repositories.RegisterRepository;
import com.iktpreobuka.schoolregister.repositories.StudentRepository;
import com.iktpreobuka.schoolregister.repositories.SubjectRepository;
import com.iktpreobuka.schoolregister.repositories.TeacherRepository;
import com.iktpreobuka.schoolregister.repositories.TeacherSubjectGroupRepository;
import com.iktpreobuka.schoolregister.services.AccountDao;
import com.iktpreobuka.schoolregister.services.EmailDao;
import com.iktpreobuka.schoolregister.services.ScheduleDao;
import com.iktpreobuka.schoolregister.services.UserDao;
import com.iktpreobuka.schoolregister.util.RESTError;



@RestController
@RequestMapping(path = "/eregister/register")
public class RegisterController {
	
	@Autowired
	private RegisterRepository registerRepository;
	
	@Autowired
	private AccountDao accountDao;

	@Autowired
	private AccountRepository accountRepository;

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
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ChildParentRepository childParentRepository;
	
	@Autowired
	private EmailDao emailDao;
	
	@RequestMapping(method = RequestMethod.GET, value = "/all")
	public ResponseEntity<?> getAllRegisters() {
		return new ResponseEntity<List<RegisterEntity>>((List<RegisterEntity>) registerRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/student/{studentId}/subject/{subjectId}")
	@Secured("ROLE_TEACHER")
	public ResponseEntity<?> addMark(@PathVariable("studentId") Integer studentId, @PathVariable("subjectId") Integer subjectId,
			@RequestBody MarkDTO mark) {

		try {
			TeacherEntity teacher = teacherRepository.findById(accountRepository.findByUsername(
					accountDao.getLoggedInUsername()).getUser().getId()).orElse(null);
			
			/*if (teacher == null || userDao.getActiveAccountForTeacher(teacher).isEmpty())
				return new ResponseEntity<RESTError>(new RESTError(5, "teacher not found"), HttpStatus.NOT_FOUND);*/
			
			StudentEntity student = studentRepository.findById(studentId).orElse(null);
			if (student == null || userDao.getActiveAccountForStudent(student).isEmpty())
				return new ResponseEntity<RESTError>(new RESTError(1, "student not found"), HttpStatus.NOT_FOUND);

			SubjectEntity subject = subjectRepository.findById(subjectId).orElse(null);
			if (subject == null || subject.getActive() == false)
				return new ResponseEntity<RESTError>(new RESTError(2, "subject not found"), HttpStatus.NOT_FOUND);
			
			if(student.getSchoolGroup() == null)
				return new ResponseEntity<RESTError>(new RESTError(4, "student not yet delegated to an group"), HttpStatus.NOT_FOUND);
			List<TeacherSubjectGroup> tsgList = scheduleDao.findSheduleForStudentAndSubject(teacher,student,subject);
			
			if(tsgList.isEmpty())
				return new ResponseEntity<RESTError>(new RESTError(50, "unable to give this studen for the subject desired"), HttpStatus.BAD_REQUEST);

			if(mark.getMarkDefinition() == null ||mark.getMarkValue() == null|| mark.getSemester() == null)
				return new ResponseEntity<RESTError>(new RESTError(3, "mark not defined"), HttpStatus.BAD_REQUEST);
			
			TeacherSubjectGroup tsg = tsgList.get(0);

			RegisterEntity reg = new RegisterEntity();
			
			reg.setSchedule(tsg);
			reg.setMarkDefinition(mark.getMarkDefinition());
			reg.setMarkValue(mark.getMarkValue());
			reg.setRegisterEntryDate(new Date());
			reg.setSemester(mark.getSemester());
			reg.setStudent(student);
			List<ParentEntity> parents = childParentRepository.findParentsByChild(student);
			if(parents.isEmpty())
				return new ResponseEntity<RESTError>(new RESTError(15, "student has no parents"), HttpStatus.NOT_FOUND);
			for(ParentEntity p : parents) {
				emailDao.sendTemplateMessage(p,reg);
			}
			
			if(parents.isEmpty())
				return new ResponseEntity<RESTError>(new RESTError(20, "child has no parents"), HttpStatus.NOT_FOUND);
			

			return new ResponseEntity<RegisterEntity>(reg, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


}
