package com.iktpreobuka.schoolregister.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.iktpreobuka.schoolregister.entities.AccountEntity;
import com.iktpreobuka.schoolregister.entities.AdminEntity;
import com.iktpreobuka.schoolregister.entities.ParentEntity;
import com.iktpreobuka.schoolregister.entities.StudentEntity;
import com.iktpreobuka.schoolregister.entities.UserEntity;
import com.iktpreobuka.schoolregister.entities.dto.ParentUpdateDTO;
import com.iktpreobuka.schoolregister.entities.dto.StudentBasicInfoUpdateDTPO;
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
	public List<AccountEntity> getActiveAccountForStudent(StudentEntity student) {

		String sql = "select a from AccountEntity a where a.role = 3 and a.active = true and a.user = " + student.getId();

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
	public List<AccountEntity> getUsersStudentAccount(UserEntity user){
		
		String sql = "select a from AccountEntity a where a.role = 3 and a.user = " + user.getId();

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
	public ParentEntity checkPropToBeChangedParent(ParentEntity exist, ParentUpdateDTO newAtr) {
		if (!(newAtr.getName() == null))
			exist.setName(newAtr.getName());
		if (!(newAtr.getSurname() == null))
			exist.setSurname(newAtr.getSurname());
		if (!(newAtr.getDateOfBirth() == null))
			exist.setDateOfBirth(newAtr.getDateOfBirth());
		if (!(newAtr.getJmbg() == null))
			exist.setJmbg(newAtr.getJmbg());
		if (!(newAtr.getEmail() == null))
			exist.setEmail(newAtr.getEmail());
		if (!(newAtr.getGender() == null))
			exist.setGender(newAtr.getGender());
		if (!(newAtr.getPhone() == null))
			exist.setPhone(newAtr.getPhone());
		
		if (!(newAtr.getStreetNumber() == null))
			exist.getParentAddress().setStreetNumber(newAtr.getStreetNumber());
		if (!(newAtr.getStreet() == null))
			exist.getParentAddress().setStreet(newAtr.getStreet());
		if (!(newAtr.getCity() == null))
			exist.getParentAddress().setCity(newAtr.getCity());
		return exist;
		}

	@Override
	public StudentEntity checkPropToBeChangedStudent(StudentEntity exist, StudentBasicInfoUpdateDTPO newAtr) {
		if (!(newAtr.getName() == null))
			exist.setName(newAtr.getName());
		if (!(newAtr.getSurname() == null))
			exist.setSurname(newAtr.getSurname());
		if (!(newAtr.getDateOfBirth() == null))
			exist.setDateOfBirth(newAtr.getDateOfBirth());
		if (!(newAtr.getJmbg() == null))
			exist.setJmbg(newAtr.getJmbg());
		if (!(newAtr.getEmail() == null))
			exist.setEmail(newAtr.getEmail());
		if (!(newAtr.getGender() == null))
			exist.setGender(newAtr.getGender());		
		if (!(newAtr.getStreetNumber() == null))
			exist.getStudentAddress().setStreetNumber(newAtr.getStreetNumber());
		if (!(newAtr.getStreet() == null))
			exist.getStudentAddress().setStreet(newAtr.getStreet());
		if (!(newAtr.getCity() == null))
			exist.getStudentAddress().setCity(newAtr.getCity());
		return exist;
		}


}
