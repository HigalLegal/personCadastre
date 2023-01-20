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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.higormorais.dto.requests.AddressRequest;
import com.higormorais.dto.responses.AddressResponse;
import com.higormorais.services.AddressService;
import com.higormorais.utils.StatusCode;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/addresses")
public class AddressResource {
	
	@Autowired
	private AddressService addressService;
	
	// ----------------------------------------------------------------------------
	
	@GetMapping
	@ApiOperation("Buscar todos os endereços.")
	@ApiResponse(code = StatusCode.OK, message = "Requisição de busca feita com êxito!")
	public ResponseEntity<Page<AddressResponse>> listAll(@PageableDefault @ApiIgnore Pageable pageable,
			@RequestParam(defaultValue = "") @ApiParam("nome da cidade") String city) {
		
		Page<AddressResponse> addresses = addressService.findAll(pageable);
		
		if(city.length() > 0) {
			addresses = addressService.searchByCity(pageable, city);
		}
		
		return ResponseEntity.ok().body(addresses);
	}
	
	@GetMapping("/{id}")
	@ApiResponses({
		@ApiResponse(code = StatusCode.OK, message = "Requisição de busca feita com êxito!"),
		@ApiResponse(code = StatusCode.NOT_FOUND, message = "Endereço não encontrado.")
	})
	public ResponseEntity<AddressResponse> addressById(@PathVariable @ApiParam("id do endereço") Integer id) {
		return ResponseEntity.ok().body(addressService.findById(id));
	}
	
	
	@PostMapping
	@ApiOperation("Adicionar um endereço")
	@ApiResponses({
		@ApiResponse(code = StatusCode.CREATED, message = "Requisição de cadastro feita com êxito!"),
		@ApiResponse(code = StatusCode.BAD_REQUEST, message = "Campo obrigatório não preenchido.")
	})
	public ResponseEntity<Void> created(@RequestBody @Valid AddressRequest addressRequest) {
		
		addressService.create(addressRequest);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	@PutMapping("/{id}")
	@ApiOperation("Atualizar um endereço")
	@ApiResponses({
		@ApiResponse(code = StatusCode.NO_CONTENT, message = "Requisição de atualização feita com êxito!"),
		@ApiResponse(code = StatusCode.BAD_REQUEST, message = "Campo obrigatório não preenchido."),
		@ApiResponse(code = StatusCode.NOT_FOUND, message = "Endereço não encontrado.")
	})
	public ResponseEntity<Void> updated(@PathVariable @ApiParam("id do endereço") Integer id, 
			@RequestBody @Valid AddressRequest addressRequest) {
		
		addressService.update(id, addressRequest);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	
	@DeleteMapping("/{id}")
	@ApiOperation("Remover um endereço")
	@ApiResponses({
		@ApiResponse(code = StatusCode.NO_CONTENT, message = "Requisição de atualização feita com êxito!"),
		@ApiResponse(code = StatusCode.NOT_FOUND, message = "Endereço não encontrado.")
	})
	public ResponseEntity<Void> delete(@PathVariable @ApiParam("id do endereço") Integer id) {
		
		addressService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
