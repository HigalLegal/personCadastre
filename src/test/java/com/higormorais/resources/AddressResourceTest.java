package com.higormorais.resources;

import static com.higormorais.utils.AddressBuildMock.createRequest;
import static com.higormorais.utils.AddressBuildMock.createResponse;
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
import com.higormorais.dto.requests.AddressRequest;
import com.higormorais.dto.responses.AddressResponse;
import com.higormorais.services.AddressService;

@WebMvcTest(AddressResource.class)
public class AddressResourceTest {
	
	@Autowired
	private MockMvc mockMVC;
	
	@Autowired
	private AddressResource addressResource;
	
	@MockBean
	private AddressService addressService;
	
	// --------------------------------------------------------------------------------------------------
	
	@BeforeEach
	public void setup() {
		this.mockMVC = standaloneSetup(addressResource)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.build();
	}
	
	// --------------------------------------------------------------------------------------------------
	
	@Test
	public void testStatusCodeListAll() throws Exception {
		
		Pageable pageable = PageRequest.of(0, 8);
		
		Page<AddressResponse> findAll = new PageImpl<>(new ArrayList<>());
		
		RequestBuilder request = MockMvcRequestBuilders.get("/addresses");
		
		when(addressService.findAll(pageable)).thenReturn(findAll);
		
		this.mockMVC.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testStatusCodeFindById() throws Exception {
		
		int idTest = 1;

		RequestBuilder request = MockMvcRequestBuilders.get("/addresses/" + idTest);

		when(addressService.findById(1)).thenReturn(createResponse());

		this.mockMVC.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void testStatusCodeCreatedAddress() throws Exception, JsonProcessingException {

		String json = objectToJSON(createRequest());
		
		RequestBuilder request = MockMvcRequestBuilders.post("/addresses")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON);

		this.mockMVC.perform(request).andExpect(MockMvcResultMatchers.status().isCreated());

	}
	
	@Test
	public void testStatusCodeCreatedAddressError() throws Exception, JsonProcessingException {
		
		AddressRequest addressRequest = createRequest();
		addressRequest.setOwnerId(null);
		
		String json = objectToJSON(addressRequest);
		
		RequestBuilder request = MockMvcRequestBuilders.post("/addresses")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON);

		this.mockMVC.perform(request).andExpect(MockMvcResultMatchers.status().isBadRequest());
		
		
	}
	
	@Test
	public void testStatusCodeUpdatedAddress() throws Exception, JsonProcessingException {

		String json = objectToJSON(createRequest());
		
		RequestBuilder request = MockMvcRequestBuilders.put("/addresses/1")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON);

		this.mockMVC.perform(request).andExpect(MockMvcResultMatchers.status().isNoContent());

	}
	
	public void testStatusCodeDeleteAddress() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders.delete("/addresses/1");
		
		this.mockMVC.perform(request).andExpect(MockMvcResultMatchers.status().isNoContent());
	}
	

}





















