package com.iktpreobuka.schoolregister.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.schoolregister.entities.AdminEntity;
import com.iktpreobuka.schoolregister.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer>{

	AdminEntity findByJmbg(String jmbg);

}
