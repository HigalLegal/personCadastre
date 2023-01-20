package com.higormorais.resources;

import static com.higormorais.utils.PersonBuildMock.*;
import static com.higormorais.utils.ParseObjectToJSON.objectToJSON;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.higormorais.dto.requests.PersonRequest;
import com.higormorais.dto.responses.AddressResponse;
import com.higormorais.dto.responses.PersonResponseAll;
import com.higormorais.services.PersonService;

@WebMvcTest(PersonResource.class)
public class PersonResourceTest {

	@Autowired
	private MockMvc mockMVC;

	@Autowired
	private PersonResource personResource;

	@MockBean
	private PersonService personService;

	@BeforeEach
	public void setup() {
		this.mockMVC = standaloneSetup(personResource)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.build();
	}

	@Test
	public void testStatusCodeListAll() throws Exception {

		Pageable pageable = PageRequest.of(0, 8);
		Page<PersonResponseAll> findAll = new PageImpl<>(new ArrayList<>());

		RequestBuilder request = MockMvcRequestBuilders.get("/persons");

		when(personService.findAll(pageable)).thenReturn(findAll);

		this.mockMVC.perform(request).andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void testStatusCodeListAddressesByPersonId() throws Exception {

		Pageable pageable = PageRequest.of(0, 8);

		Page<AddressResponse> addresses = new PageImpl<>(new ArrayList<>());

		RequestBuilder request = MockMvcRequestBuilders.get("/persons/1/addresses");

		when(personService.searchAddressByPersonId(pageable, 1)).thenReturn(addresses);

		this.mockMVC.perform(request).andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void testStatusCodeFindById() throws Exception {

		int idTest = 1;

		RequestBuilder request = MockMvcRequestBuilders.get("/persons/" + idTest);

		when(personService.findById(idTest)).thenReturn(createResponseOne());

		this.mockMVC.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testStatusCodeCreatedPerson() throws Exception, JsonProcessingException {

		String json = objectToJSON(createRequest());
		
		RequestBuilder request = MockMvcRequestBuilders.post("/persons")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON);

		this.mockMVC.perform(request).andExpect(MockMvcResultMatchers.status().isCreated());

	}
	
	@Test
	public void testStatusCodeCreatedPersonError() throws Exception, JsonProcessingException {
		
		PersonRequest personRequest = createRequest();
		personRequest.setName(null);
		
		String json = objectToJSON(personRequest);
		
		RequestBuilder request = MockMvcRequestBuilders.post("/persons")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON);

		this.mockMVC.perform(request).andExpect(MockMvcResultMatchers.status().isBadRequest());
		
	}
	
	@Test
	public void testStatusCodeUpdatedPerson() throws Exception, JsonProcessingException {
		
		String json = objectToJSON(createRequest());
		int idTest = 1;
		
		RequestBuilder request = MockMvcRequestBuilders.put("/persons/" + idTest)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON);
		
		this.mockMVC.perform(request).andExpect(MockMvcResultMatchers.status().isNoContent());
		
	}
	
	@Test
	public void testStatusCodeDeletePerson() throws Exception, JsonProcessingException {
		
		int idTest = 1;
		
		RequestBuilder request = MockMvcRequestBuilders.delete("/persons/" + idTest);
		
		this.mockMVC.perform(request).andExpect(MockMvcResultMatchers.status().isNoContent());
		
	}
	
}





















