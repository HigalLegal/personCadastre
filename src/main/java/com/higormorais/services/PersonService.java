package com.higormorais.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.higormorais.dto.requests.PersonRequest;
import com.higormorais.dto.responses.AddressResponse;
import com.higormorais.dto.responses.PersonResponseAll;
import com.higormorais.dto.responses.PersonResponseOne;

public interface PersonService {
	
	Page<PersonResponseAll> findAll(Pageable pageable);
	Page<AddressResponse> searchAddressByPersonId(Pageable pageable, Integer personId);
	PersonResponseOne findById(Integer id);
	void create(PersonRequest personRequest);
	void update(Integer id, PersonRequest personRequest);
	void delete(Integer id);

}
