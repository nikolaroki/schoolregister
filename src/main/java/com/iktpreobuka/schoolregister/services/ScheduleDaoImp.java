package com.iktpreobuka.schoolregister.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.iktpreobuka.schoolregister.entities.GroupEntity;
import com.iktpreobuka.schoolregister.entities.SubjectEntity;
import com.iktpreobuka.schoolregister.entities.TeacherSubjectGroup;

@Service
public class ScheduleDaoImp implements ScheduleDao {

	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<TeacherSubjectGroup> checkIfOnSameGrade(SubjectEntity subject, GroupEntity group) {

		String sql = "select s from GradeSubject s,GradeEntity g where s.subject =" + subject.getId()+ " and s.grade =" + group.getGroupGrade().getId();

		Query query = em.createQuery(sql);

		return query.getResultList();

	}
}
