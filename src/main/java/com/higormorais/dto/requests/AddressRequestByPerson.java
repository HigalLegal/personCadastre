package com.higormorais.dto.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class AddressRequestByPerson {
	
	@NotBlank(message = "Esse valor não pode está em branco.")
	private String publicPlace;
	
	@Size(min = 8, max = 8, message = "CEP inválido.")
	private String cep;
	
	@NotNull(message = "Esse valor não pode ser nulo!")
	private Integer number;
	
	@NotBlank(message = "Esse valor não pode está em branco.")
	private String city;
	private Boolean mainAddress;

}
