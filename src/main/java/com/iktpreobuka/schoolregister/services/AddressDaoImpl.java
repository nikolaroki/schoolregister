package com.iktpreobuka.schoolregister.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.iktpreobuka.schoolregister.entities.AddressEntity;


@Service
public class AddressDaoImpl implements AddressDao {
	
	@PersistenceContext
    EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List <AddressEntity> findIfExists(AddressEntity address) {

		String sql = "select a from AddressEntity a "
				+ "where a.streetNumber = :sn and a.city = :ci and a.street = :st";

		Query query = em.createQuery(sql);
		query.setParameter("sn", address.getStreetNumber());
		query.setParameter("ci", address.getCity());
		query.setParameter("st", address.getStreet());

		return query.getResultList();

	}
	
	@Override
	public AddressEntity checkPropToBeChanged(AddressEntity adrExist, AddressEntity adrNew) {
		if (!(adrNew.getStreetNumber() == null))
			adrExist.setStreetNumber(adrNew.getStreetNumber());
		if (!(adrNew.getCity() == null))
			adrExist.setCity(adrNew.getCity());
		if (!(adrNew.getStreet() == null))
			adrExist.setStreet(adrNew.getStreet());
		return adrExist;
		}
	
	@Override
	public AddressEntity copyAddress(AddressEntity adrExist, AddressEntity adrNew) {
		adrNew.setCity(adrExist.getCity());
		adrNew.setStreet(adrExist.getStreet());
		adrNew.setStreetNumber(adrExist.getStreetNumber());
		return adrNew;
	}
	



}
