package com.iktpreobuka.schoolregister.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iktpreobuka.schoolregister.entities.TeacherEntity;
@Repository
public interface TeacherRepository extends CrudRepository<TeacherEntity, Integer>{
	
	@Query("SELECT t FROM TeacherEntity t, AccountEntity ac where t.id = ac.user and ac.active = true and ac.role = 2")
	Iterable<TeacherEntity> findAllActive();

	@Modifying
	@Query(value = "INSERT INTO teacher (user_id,pay,title) VALUES (:id,:pay,:title)", nativeQuery = true)
	@Transactional
	void inserIntoTeacherTable(Integer id, Double pay, String title);


}
