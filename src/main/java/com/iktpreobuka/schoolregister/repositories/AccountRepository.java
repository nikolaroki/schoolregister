package com.iktpreobuka.schoolregister.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.schoolregister.entities.AccountEntity;

public interface AccountRepository extends CrudRepository<AccountEntity, Integer>{

	AccountEntity findByUsername(String username);



	

}
