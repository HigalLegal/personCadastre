package com.higormorais.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor @RequiredArgsConstructor
@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NonNull
	@Column(nullable = false)
	private String publicPlace;
	
	@NonNull
	@Column(nullable = false)
	private String cep;
	
	@NonNull
	@Column(nullable = false)
	private Integer number;
	
	@NonNull
	@Column(nullable = false)
	private String city;
	
	@NonNull
	@Column(nullable = false)
	private Boolean mainAddress;
	
	@NonNull
	@JoinColumn(nullable = false)
	@ManyToOne
	private Person owner;

}
