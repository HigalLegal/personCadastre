package com.higormorais.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.higormorais.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	
	Page<Address> findAll(Pageable pageable);
	
	@Query("select a from Address a where a.city like %:city%")
	Page<Address> searchByCity(Pageable pageable, @Param("city") String city);
	
	@Query(value = "select * from address where owner_id = ?", nativeQuery =  true)
	Page<Address> searchAddressByPersonId(Pageable pageable, Integer ownerId);

}
