package com.iktpreobuka.schoolregister.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.schoolregister.entities.RoleEntity;
import com.iktpreobuka.schoolregister.repositories.RoleRepository;
import com.iktpreobuka.schoolregister.util.RESTError;

@RestController
@RequestMapping(path = "/eregister/role")
@Secured("ROLE_ADMIN")
public class RoleController {

	@Autowired
	private RoleRepository roleRepository;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addNewRole(@RequestBody RoleEntity newRole) {
		try {
			if (newRole.getName() == null || newRole.getName() == " ") {
				return new ResponseEntity<RESTError>(new RESTError(1, "cannot create role with no name specified"),
						HttpStatus.BAD_REQUEST);
			}

			RoleEntity role = new RoleEntity();
			role.setName(newRole.getName());

			roleRepository.save(role);
			return new ResponseEntity<RoleEntity>(role, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> findByRoleById(@PathVariable Integer id) {
		try {
			RoleEntity role = roleRepository.findById(id).orElse(null);

			if (role == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return new ResponseEntity<RoleEntity>(role, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public ResponseEntity<?> getAllRoles() {
		return new ResponseEntity<List<RoleEntity>>((List<RoleEntity>) roleRepository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> updateRole(@PathVariable Integer id, @RequestBody RoleEntity updateRole) {
		try {
			RoleEntity role = roleRepository.findById(id).orElse(null);

			if (role == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			if (updateRole == null) {
				return new ResponseEntity<RESTError>(
						new RESTError(2, "at least one parameter for change needs to be specified"),
						HttpStatus.BAD_REQUEST);
			}

			if (updateRole.getName() != null || !updateRole.getName().equals(" ") || !updateRole.getName().equals("")) {
				role.setName(updateRole.getName());
			}

			roleRepository.save(role);
			return new ResponseEntity<RoleEntity>(role, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteRole(@PathVariable Integer id) {
		try {
			RoleEntity role = roleRepository.findById(id).orElse(null);

			if (role == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			roleRepository.delete(role);

			return new ResponseEntity<RoleEntity>(role, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

}
