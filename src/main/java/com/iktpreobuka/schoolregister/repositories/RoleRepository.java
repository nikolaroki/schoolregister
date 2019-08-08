package com.iktpreobuka.schoolregister.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iktpreobuka.schoolregister.entities.RoleEntity;
@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Integer>{

}
