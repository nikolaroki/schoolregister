package com.iktpreobuka.schoolregister.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iktpreobuka.schoolregister.entities.AddressEntity;
import com.iktpreobuka.schoolregister.entities.SchoolEntity;

@Repository
public interface SchoolRepository extends CrudRepository<SchoolEntity, Integer> {

	@Query("SELECT s FROM SchoolEntity s where s.active = true")
	Iterable<SchoolEntity> findAllActive();

	List<SchoolEntity> findBySchoolNameAndSchoolAddress(String schoolName, AddressEntity adr);

	

}
