package com.iktpreobuka.schoolregister.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iktpreobuka.schoolregister.entities.GradeEntity;
import com.iktpreobuka.schoolregister.entities.GradeSubject;
import com.iktpreobuka.schoolregister.entities.SubjectEntity;

@Repository
public interface GradeSubjectRepository  extends CrudRepository<GradeSubject, Integer> {

	List<GradeSubject> findBySubjectAndGrade(SubjectEntity subj, GradeEntity grade);

}
