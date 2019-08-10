package com.iktpreobuka.schoolregister.services;

import java.util.List;

import com.iktpreobuka.schoolregister.entities.GroupEntity;
import com.iktpreobuka.schoolregister.entities.StudentEntity;
import com.iktpreobuka.schoolregister.entities.SubjectEntity;
import com.iktpreobuka.schoolregister.entities.TeacherEntity;
import com.iktpreobuka.schoolregister.entities.TeacherSubjectGroup;

public interface ScheduleDao {

	List<TeacherSubjectGroup> checkIfOnSameGrade(SubjectEntity subject, GroupEntity group);

	List<TeacherSubjectGroup> findSheduleForStudentAndSubject(TeacherEntity teacher, StudentEntity student,
			SubjectEntity subject);

	
}
