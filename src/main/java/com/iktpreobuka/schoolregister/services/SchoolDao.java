package com.iktpreobuka.schoolregister.services;

import com.iktpreobuka.schoolregister.entities.SchoolEntity;
import com.iktpreobuka.schoolregister.entities.dto.SchoolDTO;

public interface SchoolDao {

	SchoolEntity checkPropToBeChanged(SchoolEntity exist, SchoolDTO newAtr);

}
