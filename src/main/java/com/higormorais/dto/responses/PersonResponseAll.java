package com.higormorais.dto.responses;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class PersonResponseAll {
	
	private Integer id;
	private String name;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate birthDate;

}
