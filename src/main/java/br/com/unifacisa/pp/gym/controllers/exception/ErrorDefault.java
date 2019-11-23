package br.com.unifacisa.pp.gym.controllers.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDefault {

	private int status;
	
	private long timestamp;
	
	private String msg;
	
	
	
	
}
