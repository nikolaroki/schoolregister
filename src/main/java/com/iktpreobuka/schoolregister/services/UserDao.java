package com.iktpreobuka.schoolregister.services;

import java.util.List;

import com.iktpreobuka.schoolregister.entities.AccountEntity;
import com.iktpreobuka.schoolregister.entities.AdminEntity;
import com.iktpreobuka.schoolregister.entities.ParentEntity;
import com.iktpreobuka.schoolregister.entities.StudentEntity;
import com.iktpreobuka.schoolregister.entities.UserEntity;
import com.iktpreobuka.schoolregister.entities.dto.ParentUpdateDTO;
import com.iktpreobuka.schoolregister.entities.dto.StudentBasicInfoUpdateDTPO;
import com.iktpreobuka.schoolregister.entities.dto.UserBasicInfoUpdateDTO;

public interface UserDao {

	List<AccountEntity> getActiveAccountForAdmin(AdminEntity admin);

	List<UserEntity> findExistingUsers(String jmbg);

	AdminEntity checkPropToBeChangedAdmin(AdminEntity exist, UserBasicInfoUpdateDTO newadm);

	List<AccountEntity> getUsersAdminAccount(UserEntity user);

	List<AccountEntity> getActiveAccountForParent(ParentEntity parent);

	List<AccountEntity> getUsersParentAccount(UserEntity user);

	ParentEntity checkPropToBeChangedParent(ParentEntity exist, ParentUpdateDTO newprt);

	List<AccountEntity> getActiveAccountForStudent(StudentEntity student);

	List<AccountEntity> getUsersStudentAccount(UserEntity user);

	StudentEntity checkPropToBeChangedStudent(StudentEntity exist, StudentBasicInfoUpdateDTPO newAtr);

	





	

}
