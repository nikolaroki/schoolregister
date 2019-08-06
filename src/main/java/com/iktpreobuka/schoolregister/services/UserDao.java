package com.iktpreobuka.schoolregister.services;

import java.util.List;

import com.iktpreobuka.schoolregister.entities.AccountEntity;
import com.iktpreobuka.schoolregister.entities.AdminEntity;

public interface UserDao {

	List<AccountEntity> checkifActiveAdmin(AdminEntity admin);

	List<AdminEntity> doesUserExists(String jmbg);

	AdminEntity addUserToAdmin(String jmbg);

	

}
