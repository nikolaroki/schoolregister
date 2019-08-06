package com.iktpreobuka.schoolregister.services;

import java.util.List;

import com.iktpreobuka.schoolregister.entities.AddressEntity;

public interface AddressDao {

	List<AddressEntity> findIfExists(AddressEntity address);

	AddressEntity checkPropToBeChanged(AddressEntity adrExist, AddressEntity adrNew);
	
	AddressEntity copyAddress(AddressEntity adrExist, AddressEntity adrNew);

	



}
