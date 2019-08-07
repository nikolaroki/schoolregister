package com.iktpreobuka.schoolregister.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iktpreobuka.schoolregister.entities.AdminEntity;

@Repository
public interface AdminRepository extends CrudRepository<AdminEntity, Integer> {

	@Query("SELECT a FROM AdminEntity a, AccountEntity ac where a.id = ac.user and ac.active = true and ac.role = 1")
	Iterable<AdminEntity> findAllActive();

	@Modifying
	@Query(value = "INSERT INTO admin (admin_id,start_date) VALUES (:id, :date)", nativeQuery = true)
	@Transactional
	void inserIntoAdminTable(Integer id, Date date);



}
