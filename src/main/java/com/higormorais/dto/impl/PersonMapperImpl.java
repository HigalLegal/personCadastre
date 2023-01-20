package com.higormorais.dto.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.higormorais.dto.PersonMapper;
import com.higormorais.dto.requests.AddressRequestByPerson;
import com.higormorais.dto.requests.PersonRequest;
import com.higormorais.dto.responses.PersonResponseAll;
import com.higormorais.dto.responses.PersonResponseOne;
import com.higormorais.entities.Address;
import com.higormorais.entities.Person;

@Component
public class PersonMapperImpl implements PersonMapper {

	@Override
	public Person toEntity(PersonRequest personRequest) {

		var person = new Person();

		person.setName(personRequest.getName());
		person.setBirthDate(personRequest.getBirthDate());

		List<Address> addresses = personRequest.getAddresses().stream().map(this::toEntityAddress)
				.collect(Collectors.toList());

		person.setAddresses(addresses);

		return person;
	}

	@Override
	public PersonResponseAll toResponseAll(Person person) {
		return new PersonResponseAll(person.getId(), person.getName(), person.getBirthDate());
	}

	@Override
	public PersonResponseOne toResponseOne(Person person) {

		var personResponse = new PersonResponseOne();

		personResponse.setId(person.getId());
		personResponse.setName(person.getName());
		personResponse.setBirthDate(dateToString(person.getBirthDate()));

		List<String> addresses = person.getAddresses().stream().map(this::addressToString).collect(Collectors.toList());

		personResponse.setAddresses(addresses);

		return personResponse;
	}

	// -----------------------------------------------------------------------------------------------

	private Address toEntityAddress(AddressRequestByPerson addressRequest) {

		var address = new Address();

		address.setPublicPlace(addressRequest.getPublicPlace());
		address.setCep(addressRequest.getCep());
		address.setNumber(addressRequest.getNumber());
		address.setCity(addressRequest.getCity());
		address.setMainAddress(addressRequest.getMainAddress());

		return address;
	}

	private String dateToString(LocalDate date) {

		int day = date.getDayOfMonth();
		String month = extractMonth(date);
		int year = date.getYear();

		return day + " de " + month + " de " + year;
	}

	private String extractMonth(LocalDate date) {

		int monthNumber = date.getMonthValue();

		String monthString = "janeiro";

		switch (monthNumber) {
			case 2 -> monthString = "fevereiro";
			case 3 -> monthString = "março";
			case 4 -> monthString = "abril";
			case 5 -> monthString = "maio";
			case 6 -> monthString = "junho";
			case 7 -> monthString = "julho";
			case 8 -> monthString = "agosto";
			case 9 -> monthString = "setembro";
			case 10 -> monthString = "outubro";
			case 11 -> monthString = "novembro";
			case 12 -> monthString = "dezembro";
		}

		return monthString;
	}

	private String addressToString(Address address) {

		String resposta = "Não.";

		if (address.getMainAddress()) {
			resposta = "Sim.";
		}

		return "Cidade: " + address.getCity() + " || " + "Logradouro: " + address.getPublicPlace() + " || "
				+ "Número: " + address.getNumber() + "|| Principal? " + resposta;
	}

}
