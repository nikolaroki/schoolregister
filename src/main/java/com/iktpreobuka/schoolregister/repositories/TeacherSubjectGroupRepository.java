package com.iktpreobuka.schoolregister.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iktpreobuka.schoolregister.entities.GroupEntity;
import com.iktpreobuka.schoolregister.entities.SubjectEntity;
import com.iktpreobuka.schoolregister.entities.TeacherEntity;
import com.iktpreobuka.schoolregister.entities.TeacherSubjectGroup;
@Repository
public interface TeacherSubjectGroupRepository extends CrudRepository<TeacherSubjectGroup, Integer>{

	List<TeacherSubjectGroup> findByTeacherAndSubjectAndSchoolGroup(TeacherEntity teacher, SubjectEntity subject, GroupEntity group);

	@Query ("SELECT tgs FROM TeacherSubjectGroup tgs where tgs.active = true")
	List<TeacherSubjectGroup> findAllActiveSchedules();

	List<TeacherSubjectGroup> findByTeacher(TeacherEntity teacher);

	List<TeacherSubjectGroup> findBySubject(SubjectEntity subject);

	List<TeacherSubjectGroup> findBySchoolGroup(GroupEntity gr);



}
