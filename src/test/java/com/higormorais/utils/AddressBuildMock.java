package com.higormorais.utils;

import com.higormorais.dto.requests.AddressRequest;
import com.higormorais.dto.responses.AddressResponse;

public class AddressBuildMock {
	
	public static AddressResponse createResponse() {
		return new AddressResponse(1, "Gerência", "63430-000", 14, "Icó", true, "Higor Morais");
	}
	
	public static AddressRequest createRequest() {
		return new AddressRequest("Prado", "63430000", 14, "Cedro", false, 1);
	}

}
