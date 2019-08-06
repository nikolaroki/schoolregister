package com.iktpreobuka.schoolregister.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.schoolregister.entities.AdminEntity;

public interface AdminRepository extends CrudRepository<AdminEntity, Integer> {

	@Query("SELECT a FROM AdminEntity a, AccountEntity ac where a.id = ac.user and ac.active = true and ac.role = 1")
	Iterable<AdminEntity> findAllActive();

}
