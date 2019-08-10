package com.iktpreobuka.schoolregister.services;

import com.iktpreobuka.schoolregister.entities.ParentEntity;
import com.iktpreobuka.schoolregister.entities.RegisterEntity;

public interface EmailDao {

	void sendTemplateMessage(ParentEntity p, RegisterEntity r) throws Exception;

}
