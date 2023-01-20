package com.higormorais.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class AddressResponse {
	
	private Integer id;
	private String publicPlace;
	private String cep;
	private Integer number;
	private String city;
	private Boolean mainAddress;
	private String owner;

}
