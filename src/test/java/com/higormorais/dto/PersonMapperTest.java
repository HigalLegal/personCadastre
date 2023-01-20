package com.higormorais.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.higormorais.dto.responses.PersonResponseOne;
import com.higormorais.entities.Address;
import com.higormorais.entities.Person;

@SpringBootTest
@DisplayName("Test PersonMapper")
public class PersonMapperTest {
	
	@Autowired
	private PersonMapper personMapper;
	
	@Test
	@DisplayName("Test formatted date")
	public void testReturnFormattedDate() {
		
		PersonResponseOne personResponse = personMapper.toResponseOne(personEntity());
		
		String birthDate = "5 de maio de 2000";
		
		assertEquals(birthDate, personResponse.getBirthDate());
		
	}
	
	@Test
	@DisplayName("Test formatted address in String")
	public void testReturnFormattedAddress() {
		PersonResponseOne personResponse = personMapper.toResponseOne(personEntity());
		
		String address = "Cidade: Icó || Logradouro: Gerencia || Número: " + 14 + "|| Principal? Sim.";
		
		assertEquals(address, personResponse.getAddresses().get(0));
		
	}
	
	private Person personEntity() {
		var person = new Person();
		
		person.setId(1);
		person.setName("Higor");
		person.setBirthDate(LocalDate.of(2000, 5, 5));
		
		var address = new Address(1, "Gerencia", "63430000", 14, "Icó", true, person);
		
		person.setAddresses(List.of(address));
		
		return person;
	}
	

}
