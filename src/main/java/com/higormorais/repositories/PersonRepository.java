package com.higormorais.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.higormorais.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

	Page<Person> findAll(Pageable pageable);

}
