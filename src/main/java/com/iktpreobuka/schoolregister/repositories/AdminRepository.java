package com.iktpreobuka.schoolregister.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.schoolregister.entities.AdminEntity;

public interface AdminRepository extends CrudRepository<AdminEntity, Integer> {

}
