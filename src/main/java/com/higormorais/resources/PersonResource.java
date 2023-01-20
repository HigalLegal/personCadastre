package com.higormorais.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.higormorais.dto.requests.PersonRequest;
import com.higormorais.dto.responses.AddressResponse;
import com.higormorais.dto.responses.PersonResponseAll;
import com.higormorais.dto.responses.PersonResponseOne;
import com.higormorais.services.PersonService;
import com.higormorais.utils.StatusCode;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/persons")
public class PersonResource {
	
	@Autowired
	private PersonService personService;
	
	// --------------------------------------------------------------------
	
	@GetMapping
	@ApiOperation("Buscar todas as pessoas.")
	@ApiResponse(code = StatusCode.OK, message = "Requisição de busca feita com êxito!")
	public ResponseEntity<Page<PersonResponseAll>> listAll(@PageableDefault @ApiIgnore Pageable pageable) {
		
		return new ResponseEntity<>(personService.findAll(pageable), HttpStatus.OK);
	}
	
	@GetMapping("/{personId}/addresses")
	@ApiOperation("Buscar endereços de uma única pessoa")
	@ApiResponses({
		@ApiResponse(code = StatusCode.OK, message = "Requisição de busca feita com êxito!"),
		@ApiResponse(code = StatusCode.NOT_FOUND, message = "Pessoa não encontrada.")
	})
	public ResponseEntity<Page<AddressResponse>> listAddressesByPersonId(@PageableDefault @ApiIgnore Pageable pageable,
			@PathVariable @ApiParam("id da pessoa") Integer personId) {
		return new ResponseEntity<>(personService.searchAddressByPersonId(pageable, personId), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@ApiOperation("Buscar uma pessoa por id")
	@ApiResponses({
		@ApiResponse(code = StatusCode.OK, message = "Requisição de busca feita com êxito!"),
		@ApiResponse(code = StatusCode.NOT_FOUND, message = "Pessoa não encontrada.")
	})
	public ResponseEntity<PersonResponseOne> personById(@PathVariable @ApiParam("id da pessoa") Integer id) {
		return new ResponseEntity<>(personService.findById(id), HttpStatus.OK);
	}
	
	@PostMapping
	@ApiOperation("Adicionar uma nova pessoa")
	@ApiResponses({
		@ApiResponse(code = StatusCode.CREATED, message = "Requisição de cadastro feita com êxito!"),
		@ApiResponse(code = StatusCode.BAD_REQUEST, message = "Campo obrigatório não preenchido.")
	})
	public ResponseEntity<Void> createdPerson(@RequestBody @Valid PersonRequest personRequest) {
		
		personService.create(personRequest);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@ApiOperation("Atualizar uma pessoa")
	@ApiResponses({
		@ApiResponse(code = StatusCode.NO_CONTENT, message = "Requisição de atualização feita com êxito!"),
		@ApiResponse(code = StatusCode.BAD_REQUEST, message = "Campo obrigatório não preenchido."),
		@ApiResponse(code = StatusCode.NOT_FOUND, message = "Pessoa não encontrada.")
	})
	public ResponseEntity<Void> updatePerson(@PathVariable @ApiParam("id da pessoa") Integer id, 
			@RequestBody @Valid PersonRequest personRequest) {
		
		personService.update(id, personRequest);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation("Remover uma pessoa")
	@ApiResponses({
		@ApiResponse(code = StatusCode.NO_CONTENT, message = "Requisição de remoção feita com êxito!"),
		@ApiResponse(code = StatusCode.NOT_FOUND, message = "Pessoa não encontrada.")
	})
	public ResponseEntity<Void> deletePerson(@PathVariable @ApiParam("id da pessoa") Integer id) {
		
		personService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
