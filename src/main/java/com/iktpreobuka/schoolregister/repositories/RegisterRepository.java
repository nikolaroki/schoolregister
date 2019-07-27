package com.iktpreobuka.schoolregister.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.schoolregister.entities.RegisterEntity;

public interface RegisterRepository extends CrudRepository<RegisterEntity, Integer> {

}
