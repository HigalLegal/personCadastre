package com.higormorais.dto;

import com.higormorais.dto.requests.AddressRequest;
import com.higormorais.dto.responses.AddressResponse;
import com.higormorais.entities.Address;

public interface AddressMapper {
	
	Address toEntity(AddressRequest addressRequest);
	AddressResponse toResponse(Address address);

}
