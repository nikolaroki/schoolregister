package com.iktpreobuka.schoolregister.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iktpreobuka.schoolregister.entities.AccountEntity;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Integer>{

	AccountEntity findByUsername(String username);

	
	@Query("SELECT a FROM AccountEntity a where a.active = true")
	Iterable<AccountEntity> findAllActive();
	
	@Query("select a from AccountEntity a where a.active = false")
	Iterable<AccountEntity> findAllInactive();




	



	

}
