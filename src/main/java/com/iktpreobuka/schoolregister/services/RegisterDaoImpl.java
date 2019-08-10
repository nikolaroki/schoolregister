package com.iktpreobuka.schoolregister.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.iktpreobuka.schoolregister.entities.ParentEntity;
import com.iktpreobuka.schoolregister.entities.RegisterEntity;
import com.iktpreobuka.schoolregister.entities.TeacherEntity;

@Service
public class RegisterDaoImpl implements RegisterDao{
	
	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<RegisterEntity> getChilrenRegForParents(ParentEntity parent) {

		String sql = "select r from RegisterEntity r,StudentParent sp where r.student = sp.child and sp.parent = " + parent.getId(); 
	

		Query query = em.createQuery(sql);

		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegisterEntity> getTeacherRegisters(TeacherEntity teacher) {
		String sql = "select r from RegisterEntity r,TeacherSubjectGroup tsg where r.teacherSubjectGroup = tsg.id and tsg.teacher = " + teacher.getId(); 
		

		Query query = em.createQuery(sql);

		return query.getResultList();
	}
}
