package com.higormorais.dto.responses;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class PersonResponseOne {
	
	private Integer id;
	private String name;
	private String birthDate;
	
	private List<String> addresses = new ArrayList<>();

}
