package com.iktpreobuka.schoolregister.services;

import org.springframework.stereotype.Service;

import com.iktpreobuka.schoolregister.entities.SchoolEntity;
import com.iktpreobuka.schoolregister.entities.dto.SchoolDTO;

@Service
public class SchoolDaoImpl implements SchoolDao {
	
	@Override
	public SchoolEntity checkPropToBeChanged(SchoolEntity exist, SchoolDTO newAtr) {		
		if (!(newAtr.getStreetNumber() == null))
			exist.getSchoolAddress().setStreetNumber(newAtr.getStreetNumber());
		if (!(newAtr.getStreet() == null))
			exist.getSchoolAddress().setStreet(newAtr.getStreet());
		if (!(newAtr.getCity() == null))
			exist.getSchoolAddress().setCity(newAtr.getCity());
		if (!(newAtr.getSchoolName() == null))
			exist.setSchoolName(newAtr.getCity());
		return exist;
		}

}
