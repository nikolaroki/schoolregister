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

import com.iktpreobuka.schoolregister.entities.AddressEntity;
import com.iktpreobuka.schoolregister.repositories.AddressRepository;
import com.iktpreobuka.schoolregister.services.AddressDao;
import com.iktpreobuka.schoolregister.util.RESTError;

@RestController
@RequestMapping(path = "/eregister/address")
@Secured("ROLE_ADMIN")
public class AddressController {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private AddressDao addressDao;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addNewAddress(@RequestBody AddressEntity adr) {

		try {
			if (!addressDao.findIfExists(adr).isEmpty()) {
				adr = addressDao.findIfExists(adr).get(0);
				if (!adr.getActive()) {
					adr.setActive(true);
					addressRepository.save(adr);
					return new ResponseEntity<AddressEntity>(adr, HttpStatus.CREATED);
				}
				return new ResponseEntity<RESTError>(new RESTError(1, "address allready existis"),
						HttpStatus.BAD_REQUEST);
			}

			if (adr.getStreetNumber() == null || adr.getStreetNumber() == " " || adr.getStreetNumber() == "") {
				return new ResponseEntity<RESTError>(new RESTError(2, "street number not specified"),
						HttpStatus.BAD_REQUEST);
			}
			if (adr.getStreet() == null || adr.getStreet() == " " || adr.getStreet() == "") {
				return new ResponseEntity<RESTError>(new RESTError(3, "street not specified"), HttpStatus.BAD_REQUEST);
			}
			if (adr.getCity() == null || adr.getCity() == " " || adr.getCity() == "") {
				return new ResponseEntity<RESTError>(new RESTError(4, "city not specified"), HttpStatus.BAD_REQUEST);
			}
			adr.setActive(true);
			addressRepository.save(adr);
			return new ResponseEntity<AddressEntity>(adr, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> findByAddressId(@PathVariable Integer id) {
		try {
			AddressEntity adr = addressRepository.findById(id).orElse(null);

			if (adr == null || adr.getActive() == false)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return new ResponseEntity<AddressEntity>(adr, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public ResponseEntity<?> getAllActiveAddresses() {
		return new ResponseEntity<List<AddressEntity>>((List<AddressEntity>) addressRepository.findAllActiveAddresses(),
				HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/all")
	public ResponseEntity<?> getAllAddresses() {
		return new ResponseEntity<List<AddressEntity>>((List<AddressEntity>) addressRepository.findAll(),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> changeAddress(@PathVariable Integer id, @RequestBody AddressEntity address) {
		try {
			AddressEntity adr = addressRepository.findById(id).orElse(null);
			if ((adr == null) || (adr.getActive() == false)) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			if (address.getStreetNumber() == null && address.getCity() == null && address.getStreet() == null)
				return new ResponseEntity<RESTError>(new RESTError(5, "atributes to be modified not specified"),
						HttpStatus.BAD_REQUEST);
			AddressEntity tempAdr = new AddressEntity();
			tempAdr = addressDao.copyAddress(adr, tempAdr);
			tempAdr = addressDao.checkPropToBeChanged(tempAdr, address);
			if (!addressDao.findIfExists(tempAdr).isEmpty()) {
				tempAdr = addressDao.findIfExists(tempAdr).get(0);
				if (!tempAdr.getActive()) {
					tempAdr.setActive(true);
					adr.setActive(false);
					addressRepository.save(tempAdr);
					addressRepository.save(adr);
					return new ResponseEntity<AddressEntity>(tempAdr, HttpStatus.OK);
				}
				return new ResponseEntity<RESTError>(new RESTError(1, "address allready existis"),
						HttpStatus.BAD_REQUEST);}

			addressRepository.save(adr);
			return new ResponseEntity<AddressEntity>(adr, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteAddressById(@PathVariable Integer id) {
		try {
			AddressEntity adr = addressRepository.findById(id).orElse(null);
			if (adr == null || adr.getActive() == false)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			adr.setActive(false);
			addressRepository.save(adr);
			return new ResponseEntity<AddressEntity>(adr, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
