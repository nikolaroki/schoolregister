package com.iktpreobuka.schoolregister.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iktpreobuka.schoolregister.entities.GradeEntity;
@Repository
public interface GradeRepository extends CrudRepository<GradeEntity, Integer>{

	List<GradeEntity> findByName(String name);

}
