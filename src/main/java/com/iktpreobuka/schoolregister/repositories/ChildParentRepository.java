package com.iktpreobuka.schoolregister.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iktpreobuka.schoolregister.entities.ParentEntity;
import com.iktpreobuka.schoolregister.entities.StudentEntity;
import com.iktpreobuka.schoolregister.entities.StudentParent;

@Repository
public interface ChildParentRepository extends CrudRepository<StudentParent, Integer>{

	List<StudentParent> findByChildAndParent(StudentEntity student, ParentEntity parent);

	
    @Query("SELECT sp.child FROM StudentParent sp where sp.parent = :parent")
	List<StudentEntity> findChildrenByParent(ParentEntity parent);

    @Query("SELECT sp.parent FROM StudentParent sp where sp.child = :child")
	List<ParentEntity> findParentsByChild(StudentEntity child);

	
		


}
