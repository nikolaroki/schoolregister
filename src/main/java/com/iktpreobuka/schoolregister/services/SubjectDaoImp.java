package com.iktpreobuka.schoolregister.services;

import org.springframework.stereotype.Service;

import com.iktpreobuka.schoolregister.entities.SubjectEntity;
import com.iktpreobuka.schoolregister.entities.dto.SubjectDTO;

@Service
public class SubjectDaoImp implements SubjectDao {
	
	@Override
	public SubjectEntity copySubject(SubjectEntity subjExist, SubjectEntity subjNew) {
		subjNew.setName(subjExist.getName());
		subjNew.setClassesPerWeek(subjExist.getClassesPerWeek());
		return subjNew;
	}
	
	@Override
	public SubjectEntity checkPropToBeChanged(SubjectEntity exist, SubjectDTO neww) {
		if (!(neww.getName() == null))
			exist.setName(neww.getName());
		if (!(neww.getClassesPerWeek() == null))
			exist.setClassesPerWeek(neww.getClassesPerWeek());
	
		return exist;
		}

}
