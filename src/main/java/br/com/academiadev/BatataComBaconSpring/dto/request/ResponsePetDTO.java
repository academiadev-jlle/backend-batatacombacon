package br.com.academiadev.BatataComBaconSpring.dto.request;

import java.util.ArrayList;

import br.com.academiadev.BatataComBaconSpring.enums.Especie;
import br.com.academiadev.BatataComBaconSpring.enums.Objetivo;
import br.com.academiadev.BatataComBaconSpring.enums.Porte;
import br.com.academiadev.BatataComBaconSpring.enums.Sexo;
import br.com.academiadev.BatataComBaconSpring.model.Localizacao;
import lombok.Data;

@Data
public class ResponsePetDTO {
	
	private Long id;
	
	private String nome;
	
	private String descricao; 
	
	private Sexo sexo;
	
	private Porte porte;

	private Especie especie;

	private Objetivo objetivo;
	
	private Localizacao localPet;
	
	private ArrayList<Long> fotos;
	
	private Long idUsuario;
	
	private String created_at;

}
