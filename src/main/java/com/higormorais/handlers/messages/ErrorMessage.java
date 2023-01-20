package com.higormorais.handlers.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ErrorMessage {
	
	private String title;
	private Integer status;
	private String message;

}
