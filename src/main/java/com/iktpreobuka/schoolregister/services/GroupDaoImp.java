package com.iktpreobuka.schoolregister.services;

import org.springframework.stereotype.Service;

import com.iktpreobuka.schoolregister.entities.GroupEntity;

@Service
public class GroupDaoImp implements GroupDao {
	
	@Override
	public Integer getIncreasesGrade(GroupEntity group) {
		Integer temp = group.getGroupGrade().getId();
		temp = temp+1;
		return temp;
	}
	

}
