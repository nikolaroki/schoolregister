package com.iktpreobuka.schoolregister.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iktpreobuka.schoolregister.entities.GroupEntity;

@Repository
public interface GroupRepository extends CrudRepository<GroupEntity, Integer>  {

	
	@Query ("SELECT g FROM GroupEntity g where g.active = true")
	List<GroupEntity> findAllActiveGroups();
}
