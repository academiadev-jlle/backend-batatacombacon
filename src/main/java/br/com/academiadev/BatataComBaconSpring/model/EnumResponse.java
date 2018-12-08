package br.com.academiadev.BatataComBaconSpring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnumResponse {
	
	private String nome;
	
	private String descricao;

}
