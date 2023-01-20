package com.higormorais.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.higormorais.dto.responses.AddressResponse;
import com.higormorais.entities.Address;
import com.higormorais.entities.Person;

@SpringBootTest
public class AddressMapperTest {
	
	@Autowired
	private AddressMapper addressMapper;
	
	@Test
	public void testNameOwner() {
		
		AddressResponse addressResponse = addressMapper.toResponse(address());
		
		String name = "Higor";
		
		assertEquals(name, addressResponse.getOwner());
		
	}
	
	@Test
	public void testFormattedCep() {
		AddressResponse addressResponse = addressMapper.toResponse(address());
		
		String cep = "63430-000";
		
		assertEquals(cep, addressResponse.getCep());
	}
	
	
	private Address address() {
		
		var addressEntity = new Address();
		
		addressEntity.setId(1);
		addressEntity.setPublicPlace("Gerência");
		addressEntity.setCep("63430000");
		addressEntity.setNumber(14);
		addressEntity.setCity("Icó");
		addressEntity.setMainAddress(Boolean.TRUE);
		addressEntity.setOwner(new Person(1, "Higor", LocalDate.now(), List.of(addressEntity)));
		
		return addressEntity;
	}

}
