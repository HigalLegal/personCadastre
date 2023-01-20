package com.higormorais.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.higormorais.dto.AddressMapper;
import com.higormorais.dto.PersonMapper;
import com.higormorais.dto.requests.PersonRequest;
import com.higormorais.dto.responses.AddressResponse;
import com.higormorais.dto.responses.PersonResponseAll;
import com.higormorais.dto.responses.PersonResponseOne;
import com.higormorais.entities.Person;
import com.higormorais.repositories.AddressRepository;
import com.higormorais.repositories.PersonRepository;
import com.higormorais.services.PersonService;

import javax.persistence.EntityNotFoundException;

@Service
public class PersonServiceImpl implements PersonService {
	
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	AddressRepository addressRepository;;
	
	@Autowired
	PersonMapper personMapper;
	
	@Autowired
	AddressMapper addressMapper;
	
	// --------------------------------------------------------------------

	@Override
	public Page<PersonResponseAll> findAll(Pageable pageable) {
		
		Page<PersonResponseAll> allPerson = personRepository.findAll(pageable).map(personMapper::toResponseAll);
		
		return allPerson;
	}
	
	@Override
	public Page<AddressResponse> searchAddressByPersonId(Pageable pageable, Integer personId) {
		
		if(personRepository.findById(personId).isEmpty()) {
			throw new EntityNotFoundException("Não encontrado.");
		}
		
		return addressRepository.searchAddressByPersonId(pageable, personId)
				.map(addressMapper::toResponse);
	}

	@Override
	public PersonResponseOne findById(Integer id) {
		
		Person person = personRepository.findById(id).
				orElseThrow(() -> new EntityNotFoundException("Não encontrado."));
		
		return personMapper.toResponseOne(person);
	}

	@Override
	public void create(PersonRequest personRequest) {
		
		Person person = personMapper.toEntity(personRequest);
		
		personRepository.save(person);
		
	}

	@Override
	public void update(Integer id, PersonRequest personRequest) {
		
		if(personRepository.findById(id).isEmpty()) {
			throw new EntityNotFoundException("Não encontrado.");
		}

		Person person = personMapper.toEntity(personRequest);
		person.setId(id);
		
		personRepository.save(person);
		
	}

	@Override
	public void delete(Integer id) {
		
		personRepository.deleteById(id);
	}
	
	

}
