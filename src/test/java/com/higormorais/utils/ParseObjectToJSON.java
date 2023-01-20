package com.higormorais.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ParseObjectToJSON {

	public static String objectToJSON(Object object) throws JsonProcessingException {

		ObjectMapper objAux = new ObjectMapper();
		ObjectMapper obj = objAux.findAndRegisterModules();

		return obj.writeValueAsString(object);
	}

}
