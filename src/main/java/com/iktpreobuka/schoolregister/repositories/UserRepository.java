package com.iktpreobuka.schoolregister.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iktpreobuka.schoolregister.entities.AdminEntity;
import com.iktpreobuka.schoolregister.entities.UserEntity;
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer>{

	AdminEntity findByJmbg(String jmbg);

}
