package com.higormorais.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.higormorais.dto.AddressMapper;
import com.higormorais.dto.requests.AddressRequest;
import com.higormorais.dto.responses.AddressResponse;
import com.higormorais.entities.Address;
import com.higormorais.repositories.AddressRepository;
import com.higormorais.services.AddressService;

import javax.persistence.EntityNotFoundException;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	AddressMapper addressMapper;
	
	// ---------------------------------------------------------------------------

	@Override
	public Page<AddressResponse> findAll(Pageable pageable) {
		return addressRepository.findAll(pageable)
				.map(addressMapper::toResponse);
	}

	@Override
	public Page<AddressResponse> searchByCity(Pageable pageable, String city) {
		return addressRepository.searchByCity(pageable, city)
				.map(addressMapper::toResponse);
	}

	@Override
	public AddressResponse findById(Integer id) {
		
		Address address = addressRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado"));
		
		return addressMapper.toResponse(address);
	}

	@Override
	public void create(AddressRequest addressRequest) {
		
		Address address = addressMapper.toEntity(addressRequest);
		
		addressRepository.save(address);
		
	}

	@Override
	public void update(Integer id, AddressRequest addressRequest) {
		
		if(addressRepository.findById(id).isEmpty()) {
			throw new EntityNotFoundException("Não encontrado.");
		}
		
		Address address = addressMapper.toEntity(addressRequest);
		address.setId(id);
		
		addressRepository.save(address);
		
	}

	@Override
	public void delete(Integer id) {
		addressRepository.deleteById(id);
	}

}
