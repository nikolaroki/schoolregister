package com.iktpreobuka.schoolregister.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iktpreobuka.schoolregister.entities.ParentEntity;

@Repository
public interface ParentRepository extends CrudRepository<ParentEntity, Integer> {
	
	@Query("SELECT p FROM ParentEntity p, AccountEntity ac where p.id = ac.user and ac.active = true and ac.role = 4")
	Iterable<ParentEntity> findAllActive();
	

	@Modifying
	@Query(value = "INSERT INTO parent (user_id,address,phone,gender) VALUES (:id, :address, :phone, :gender)", nativeQuery = true)
	@Transactional
	void inserIntoParentTable(Integer id, Integer address, String phone, Integer gender);

}
