package com.iktpreobuka.schoolregister.services;

import java.util.List;

import com.iktpreobuka.schoolregister.entities.ParentEntity;
import com.iktpreobuka.schoolregister.entities.RegisterEntity;
import com.iktpreobuka.schoolregister.entities.TeacherEntity;

public interface RegisterDao {

	List<RegisterEntity> getChilrenRegForParents(ParentEntity parent);

	List<RegisterEntity> getTeacherRegisters(TeacherEntity teacher);

}
