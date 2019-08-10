package com.iktpreobuka.schoolregister.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iktpreobuka.schoolregister.entities.RegisterEntity;
import com.iktpreobuka.schoolregister.entities.StudentEntity;

@Repository
public interface RegisterRepository extends CrudRepository<RegisterEntity, Integer> {

	List<RegisterEntity> findByStudent(StudentEntity student);
	
	

}
