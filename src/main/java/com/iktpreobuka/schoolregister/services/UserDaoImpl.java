package com.iktpreobuka.schoolregister.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.iktpreobuka.schoolregister.entities.AccountEntity;
import com.iktpreobuka.schoolregister.entities.AdminEntity;
import com.iktpreobuka.schoolregister.entities.ParentEntity;
import com.iktpreobuka.schoolregister.entities.UserEntity;
import com.iktpreobuka.schoolregister.entities.dto.ParentUpdateDTO;
import com.iktpreobuka.schoolregister.entities.dto.UserBasicInfoUpdateDTO;

@Service
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<AccountEntity> getActiveAccountForAdmin(AdminEntity admin) {

		String sql = "select a from AccountEntity a where a.role = 1 and a.active = true and a.user = " + admin.getId();

		Query query = em.createQuery(sql);

		return query.getResultList();

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AccountEntity> getActiveAccountForParent(ParentEntity parent) {

		String sql = "select a from AccountEntity a where a.role = 4 and a.active = true and a.user = " + parent.getId();

		Query query = em.createQuery(sql);

		return query.getResultList();

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AccountEntity> getUsersAdminAccount(UserEntity user){
		
		String sql = "select a from AccountEntity a where a.role = 1 and a.user = " + user.getId();

		Query query = em.createQuery(sql);

		return query.getResultList();
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AccountEntity> getUsersParentAccount(UserEntity user){
		
		String sql = "select a from AccountEntity a where a.role = 4 and a.user = " + user.getId();

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
	
	
	
	@Override
	public AdminEntity checkPropToBeChangedAdmin(AdminEntity exist, UserBasicInfoUpdateDTO newadm) {
		if (!(newadm.getName() == null))
			exist.setName(newadm.getName());
		if (!(newadm.getSurname() == null))
			exist.setSurname(newadm.getSurname());
		if (!(newadm.getDateOfBirth() == null))
			exist.setDateOfBirth(newadm.getDateOfBirth());
		if (!(newadm.getJmbg() == null))
			exist.setJmbg(newadm.getJmbg());
		if (!(newadm.getEmail() == null))
			exist.setEmail(newadm.getEmail());
		return exist;
		}
	
	@Override
	public ParentEntity checkPropToBeChangedParent(ParentEntity exist, ParentUpdateDTO newprt) {
		if (!(newprt.getName() == null))
			exist.setName(newprt.getName());
		if (!(newprt.getSurname() == null))
			exist.setSurname(newprt.getSurname());
		if (!(newprt.getDateOfBirth() == null))
			exist.setDateOfBirth(newprt.getDateOfBirth());
		if (!(newprt.getJmbg() == null))
			exist.setJmbg(newprt.getJmbg());
		if (!(newprt.getEmail() == null))
			exist.setEmail(newprt.getEmail());
		if (!(newprt.getGender() == null))
			exist.setGender(newprt.getGender());
		if (!(newprt.getPhone() == null))
			exist.setPhone(newprt.getPhone());
		
		if (!(newprt.getStreetNumber() == null))
			exist.getParentAddress().setStreetNumber(newprt.getStreetNumber());
		if (!(newprt.getStreet() == null))
			exist.getParentAddress().setStreet(newprt.getStreet());
		if (!(newprt.getCity() == null))
			exist.getParentAddress().setCity(newprt.getCity());
		return exist;
		}


}
