package com.higormorais.dto.impl;

import org.springframework.stereotype.Component;

import com.higormorais.dto.AddressMapper;
import com.higormorais.dto.requests.AddressRequest;
import com.higormorais.dto.responses.AddressResponse;
import com.higormorais.entities.Address;
import com.higormorais.entities.Person;

@Component
public class AddressMaperImpl implements AddressMapper {

	@Override
	public Address toEntity(AddressRequest addressRequest) {

		var address = new Address();

		address.setPublicPlace(addressRequest.getPublicPlace());
		address.setCep(addressRequest.getCep());
		address.setNumber(addressRequest.getNumber());
		address.setCity(addressRequest.getCity());
		address.setMainAddress(addressRequest.getMainAddress());

		var person = new Person();
		person.setId(addressRequest.getOwnerId());

		address.setOwner(person);

		return address;
	}

	@Override
	public AddressResponse toResponse(Address address) {

		var addressResponse = new AddressResponse();

		addressResponse.setId(address.getId());
		addressResponse.setPublicPlace(address.getPublicPlace());
		addressResponse.setCep(formattedCep(address.getCep()));
		addressResponse.setNumber(address.getNumber());
		addressResponse.setCity(address.getCity());
		addressResponse.setMainAddress(address.getMainAddress());
		addressResponse.setOwner(address.getOwner().getName());

		return addressResponse;
	}
	
	private String formattedCep(String cep) {
		return cep.substring(0,5) + "-" + cep.substring(5);
	}

}
