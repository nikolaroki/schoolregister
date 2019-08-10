package com.iktpreobuka.schoolregister.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iktpreobuka.schoolregister.entities.RegisterEntity;

@Repository
public interface RegisterRepository extends CrudRepository<RegisterEntity, Integer> {
	
	

}
