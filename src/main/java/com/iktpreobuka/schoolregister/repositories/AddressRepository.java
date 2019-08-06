package com.iktpreobuka.schoolregister.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iktpreobuka.schoolregister.entities.AddressEntity;
@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Integer>{

	@Query ("SELECT a FROM AddressEntity a where a.active = true")
	List<AddressEntity> findAllActiveAddresses();
	
	

}
