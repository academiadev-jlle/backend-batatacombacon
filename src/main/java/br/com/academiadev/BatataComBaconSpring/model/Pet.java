package br.com.academiadev.BatataComBaconSpring.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

import br.com.academiadev.BatataComBaconSpring.enums.Objetivo;
import br.com.academiadev.BatataComBaconSpring.enums.Especie;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {

	@Id
	@GeneratedValue
	@ApiModelProperty(example = "1", name = "Identificador do Pet")
	private Long id;

	@NotNull
	@ManyToOne
	@ApiModelProperty(name = "Usuário Criador")
	private User usuario;

	@NotBlank
	@Size(min = 3, max = 30)
	@ApiModelProperty(example = "Rex", name = "Nome do Pet")
	private String nome;
	
	@NotNull
	@ApiModelProperty(example = "True", name = "Sexo do pet")
	private Boolean macho;

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

	@NotNull
	@ApiModelProperty(example = "2018-12-31", name = "Data de cadastro do Pet")
	private LocalDate dataCriacao;

	@Nullable
	@ApiModelProperty(example = "2018-12-31", name = "Data em que o pet nasceu / foi perdido / foi encontrado")
	private LocalDate dataPet;
}
