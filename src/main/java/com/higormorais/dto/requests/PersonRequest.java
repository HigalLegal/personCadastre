package com.higormorais.dto.requests;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class PersonRequest {
	
	@NotBlank(message = "Esse valor não pode está em branco.")
	private String name;
	
	@JsonFormat(pattern = "dd/MM/yyyy") @NotNull(message = "Esse valor não pode ser nulo!")
	private LocalDate birthDate;
	
	@NotNull(message = "Esse valor não pode ser nulo!")
	private List<AddressRequestByPerson> addresses = new ArrayList<>();

}
