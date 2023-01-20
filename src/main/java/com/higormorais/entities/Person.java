package com.higormorais.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String name;

	private LocalDate birthDate;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
	private List<Address> addresses = new ArrayList<>();
	
	// ----------------------------------------------------------------------------------------------
	
	public Person() {}

	public Person(Integer id, String name, LocalDate birthDate, List<Address> addresses) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.addresses.addAll(addresses);
	}

	public Person(String name, LocalDate birthDate, List<Address> addresses) {
		this.name = name;
		this.birthDate = birthDate;
		this.addresses.addAll(addresses);
	}
	
	// ----------------------------------------------------------------------------------------------

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses.addAll(addresses);
		this.addresses.forEach((address) -> {
			address.setOwner(this);
		});
	}
	
	
	
	

}
