package com.iktpreobuka.schoolregister.services;

import java.util.List;

import com.iktpreobuka.schoolregister.entities.AccountEntity;
import com.iktpreobuka.schoolregister.entities.AdminEntity;
import com.iktpreobuka.schoolregister.entities.UserEntity;

public interface UserDao {

	List<AccountEntity> checkifActiveAdmin(AdminEntity admin);

	List<UserEntity> findExistingUsers(String jmbg);





	

}
