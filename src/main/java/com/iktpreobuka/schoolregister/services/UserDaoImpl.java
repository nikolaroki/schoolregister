package com.iktpreobuka.schoolregister.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.iktpreobuka.schoolregister.entities.AccountEntity;
import com.iktpreobuka.schoolregister.entities.AdminEntity;

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
	public List<AdminEntity> doesUserExists(String userJmbg) {

		String sql = "select u.id from UserEntity u where u.jmbg = '" + userJmbg + "'";

		Query query = em.createQuery(sql);

		List<AdminEntity> result = query.getResultList();

		return result;

	}

	@Override
	public AdminEntity addUserToAdmin(String userJmbg) {

		/*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String todayDate = format.format(new Date());*/
		
		String sql = "insert into AdminEntity(id) select u.id from "
				+ "UserEntity u where u.jmbg = :jb";
		Query query = em.createQuery(sql);
		query.setParameter("jb", userJmbg);

		int res = query.executeUpdate();
		System.out.println(res);
		
		String sql1 = "select a from AdminEntity a " + "where a.id = (select u.id from UserEntity u where u.jmbg = '" + userJmbg + "'";
		Query query1 = em.createQuery(sql1);
		return (AdminEntity) query1.getResultList().get(0);

		
		
		

	}

}
