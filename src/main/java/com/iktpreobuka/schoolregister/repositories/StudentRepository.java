package com.iktpreobuka.schoolregister.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.schoolregister.entities.StudentEntity;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer>{
	
}
