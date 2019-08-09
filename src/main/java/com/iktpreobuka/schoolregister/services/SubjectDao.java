package com.iktpreobuka.schoolregister.services;

import com.iktpreobuka.schoolregister.entities.SubjectEntity;
import com.iktpreobuka.schoolregister.entities.dto.SubjectDTO;

public interface SubjectDao {

	SubjectEntity copySubject(SubjectEntity subjExist, SubjectEntity subjNew);


	SubjectEntity checkPropToBeChanged(SubjectEntity exist, SubjectDTO neww);

}
