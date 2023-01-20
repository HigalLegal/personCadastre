package com.higormorais.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.higormorais.dto.requests.AddressRequest;
import com.higormorais.dto.responses.AddressResponse;

public interface AddressService {
	
	Page<AddressResponse> findAll(Pageable pageable);
	Page<AddressResponse> searchByCity(Pageable pageable, String city);
	AddressResponse findById(Integer id);
	void create(AddressRequest addressRequest);
	void update(Integer id, AddressRequest addressRequest);
	void delete(Integer id);

}
