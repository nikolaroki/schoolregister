package com.iktpreobuka.schoolregister.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iktpreobuka.schoolregister.entities.SubjectEntity;



@Repository
public interface SubjectRepository extends CrudRepository<SubjectEntity, Integer>{

	@Query ("SELECT s FROM SubjectEntity s where s.active = true")
	List<SubjectEntity> findAllActiveSubjects();

	List<SubjectEntity> findByName(String name);

	List<SubjectEntity> findByNameAndClassesPerWeek(String name, Integer classesPerWeek);

}
