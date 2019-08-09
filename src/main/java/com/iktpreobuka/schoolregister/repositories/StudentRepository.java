package com.iktpreobuka.schoolregister.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iktpreobuka.schoolregister.entities.StudentEntity;
@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Integer>{
	
	@Query("SELECT s FROM StudentEntity s, AccountEntity ac where s.id = ac.user and ac.active = true and ac.role = 3")
	Iterable<StudentEntity> findAllActive();

	@Modifying
	@Query(value = "INSERT INTO student (user_id,address,gender) VALUES (:id,:address,:gender)", nativeQuery = true)
	@Transactional
	void inserIntoStudentTable(Integer id, Integer address, Integer gender);


	
}
