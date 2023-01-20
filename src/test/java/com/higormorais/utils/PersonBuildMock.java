package com.higormorais.utils;

import java.time.LocalDate;
import java.util.List;

import com.higormorais.dto.requests.AddressRequestByPerson;
import com.higormorais.dto.requests.PersonRequest;
import com.higormorais.dto.responses.PersonResponseOne;

public class PersonBuildMock {

	// ---------------------------------------------------------------------------

	public static PersonResponseOne createResponseOne() {

		String address1 = "Cidade: Icó || Logradouro: Gerência || Número: 14 || Principal? Sim.";
		String address2 = "Cidade: Cedro || Logradouro: Prado || Número: 194 || Principal? Não.";

		var personResponse = new PersonResponseOne(1, "Higor", "5 de maio de 2000", List.of(address1, address2));

		return personResponse;
	}

	public static PersonRequest createRequest() {

		var address1 = new AddressRequestByPerson("Gerência", "63430000", 14, "Icó", true);
		var address2 = new AddressRequestByPerson("Prado", "63430000", 31, "Cedro", false);

		return new PersonRequest("Higor", LocalDate.of(2000, 5, 5), List.of(address1, address2));
	}

}
