package br.com.unifacisa.pp.gym.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AtividadeDTO {
	
	private Integer pessoaId;
	
	private String titulo;
	
	private String descricao;
	
	private Integer funcionarioId;
	
	private Date inicio;
	
}
