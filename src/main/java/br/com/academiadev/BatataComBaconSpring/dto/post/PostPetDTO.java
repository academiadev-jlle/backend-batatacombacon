package br.com.academiadev.BatataComBaconSpring.dto.post;

import java.util.ArrayList;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import br.com.academiadev.BatataComBaconSpring.enums.Especie;
import br.com.academiadev.BatataComBaconSpring.enums.Objetivo;
import br.com.academiadev.BatataComBaconSpring.enums.Porte;
import br.com.academiadev.BatataComBaconSpring.enums.Sexo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Pet")
public class PostPetDTO {
	
	@NotBlank
	@Size(min = 3, max = 30)
	@ApiModelProperty(example = "Rex", name = "Nome do Pet")
	private String nome;
	
	@NotNull
	@ApiModelProperty(example = "Oi, eu sou o seu Pet <3", name = "Resumo ou Descrição")
	private String descricao;
	
	@NotNull
	@ApiModelProperty(example = "MACHO", name = "Sexo do pet")
	private Sexo sexo;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@ApiModelProperty(example = "PEQUENO", name = "Porte do Pet")
	private Porte porte;

	@NotNull
	@Enumerated(EnumType.STRING)
	@ApiModelProperty(example = "FELINO", name = "Espécie do Pet")
	private Especie especie;

	@NotNull
	@Enumerated(EnumType.STRING)
	@ApiModelProperty(example = "DOACAO", name = "Objetivo do cadastro deste Pet")
	private Objetivo objetivo;
	
	@NotBlank
	@ApiModelProperty(example = "Morro da vó Salvelina", name = "Local onde o Pet foi encontrado / perdido / acolhido")
	private String localPet;
	
	@ApiModelProperty(example = "[]", name = "IDs das imagens do Pet" )
	private ArrayList<Long> fotos;
	
	@NotNull
	@ApiModelProperty(example = "1", name = "Identificador do Usuário Criador")
	private Long idUsuario;
}
