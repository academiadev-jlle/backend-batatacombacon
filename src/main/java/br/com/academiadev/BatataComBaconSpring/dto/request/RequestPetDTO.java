package br.com.academiadev.BatataComBaconSpring.dto.request;

import java.util.ArrayList;

import br.com.academiadev.BatataComBaconSpring.enums.Especie;
import br.com.academiadev.BatataComBaconSpring.enums.Objetivo;
import br.com.academiadev.BatataComBaconSpring.enums.Porte;
import br.com.academiadev.BatataComBaconSpring.enums.Sexo;
import lombok.Data;

@Data
public class RequestPetDTO {
	
	private Long id;
	
	private String nome;
	
	private String descricao; 
	
	private Sexo sexo;
	
	private Porte porte;

	private Especie especie;

	private Objetivo objetivo;
	
	private String localPet;
	
	private ArrayList<Long> fotos;
	
	private Long idUsuario;
	
	private String created_at;

}
