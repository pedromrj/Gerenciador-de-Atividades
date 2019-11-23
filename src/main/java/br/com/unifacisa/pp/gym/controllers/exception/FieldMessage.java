package br.com.unifacisa.pp.gym.controllers.exception;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldMessage implements Serializable{

		private static final long serialVersionUID = 1L;

		private String fieldName;
		
		private String message;

}
