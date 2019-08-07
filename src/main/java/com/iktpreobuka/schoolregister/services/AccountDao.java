package com.iktpreobuka.schoolregister.services;

import java.util.List;

import com.iktpreobuka.schoolregister.entities.AccountEntity;

public interface AccountDao {

	Boolean doesAccountExists(String username);

	List<AccountEntity> findByUserId(Integer userId);

}
