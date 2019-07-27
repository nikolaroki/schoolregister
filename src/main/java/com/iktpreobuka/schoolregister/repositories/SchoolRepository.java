package com.iktpreobuka.schoolregister.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.schoolregister.entities.SchoolEntity;

public interface SchoolRepository extends CrudRepository<SchoolEntity, Integer> {

}
