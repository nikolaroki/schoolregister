package com.iktpreobuka.schoolregister.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.schoolregister.entities.UserEntity;
import com.iktpreobuka.schoolregister.repositories.UserRepository;

@RestController
@RequestMapping(path = "/eregister/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> getAll() {
		return new ResponseEntity<List<UserEntity>>((List<UserEntity>) userRepository.findAll(), HttpStatus.OK);
	}
	
	

}
