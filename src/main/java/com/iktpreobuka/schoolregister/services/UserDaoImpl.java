package com.iktpreobuka.schoolregister.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.iktpreobuka.schoolregister.entities.AccountEntity;
import com.iktpreobuka.schoolregister.entities.AdminEntity;
import com.iktpreobuka.schoolregister.entities.UserEntity;

@Service
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<AccountEntity> checkifActiveAdmin(AdminEntity admin) {

		String sql = "select a from AccountEntity a where a.role = 1 and a.active = true and a.user = " + admin.getId();

		Query query = em.createQuery(sql);

		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserEntity> findExistingUsers(String userJmbg) {

		String sql = "select u from UserEntity u where u.jmbg = '" + userJmbg + "'";

		Query query = em.createQuery(sql);

		return  query.getResultList();

	}


}
