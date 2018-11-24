package br.com.academiadev.BatataComBaconSpring.dto.request;

import br.com.academiadev.BatataComBaconSpring.enums.Especie;
import br.com.academiadev.BatataComBaconSpring.enums.Objetivo;
import br.com.academiadev.BatataComBaconSpring.enums.Porte;
import br.com.academiadev.BatataComBaconSpring.enums.Sexo;
import lombok.Data;

@Data
public class RequestPetDTO {
	
	private Long id;
	
	private String nome;
	
	private Sexo sexo;
	
	private Porte porte;

	private Especie especie;

	private Objetivo objetivo;
	
	private String localPet;
	
	private Long idUsuario;
	
	private String created_at;

}
