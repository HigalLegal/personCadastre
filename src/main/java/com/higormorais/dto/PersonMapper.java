package com.higormorais.dto;

import com.higormorais.dto.requests.PersonRequest;
import com.higormorais.dto.responses.PersonResponseAll;
import com.higormorais.dto.responses.PersonResponseOne;
import com.higormorais.entities.Person;

public interface PersonMapper {
	
	Person toEntity(PersonRequest personRequest);
	PersonResponseAll toResponseAll(Person person);
	PersonResponseOne toResponseOne(Person person);

}
