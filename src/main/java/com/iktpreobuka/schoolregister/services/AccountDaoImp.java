package com.iktpreobuka.schoolregister.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.iktpreobuka.schoolregister.entities.AccountEntity;
@Service
public class AccountDaoImp implements AccountDao{
	
	@PersistenceContext
    EntityManager em;
	

	@SuppressWarnings("unchecked")
	@Override
	public Boolean doesAccountExists(String accUsername) {

		String sql = "select a from AccountEntity a where a.username = '" + accUsername + "'";
		Query query = em.createQuery(sql);
		
		List<AccountEntity> result = query.getResultList();
		
		if(result.isEmpty())
			return false;
		else
			return true;

	}

		
		
	}


