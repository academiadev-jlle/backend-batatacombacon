package br.com.academiadev.BatataComBaconSpring.model;

import java.time.LocalDate;
import java.util.Optional;

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

@Entity
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getMacho() {
		return macho;
	}

	public void setMacho(Boolean macho) {
		this.macho = macho;
	}

	public Especie getEspecie() {
		return especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

	public Objetivo getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(Objetivo objetivo) {
		this.objetivo = objetivo;
	}

	public String getLocalPet() {
		return localPet;
	}

	public void setLocalPet(String localPet) {
		this.localPet = localPet;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Optional<LocalDate> getDataPet() {
		return Optional.ofNullable(dataPet);
	}

	public void setDataPet(LocalDate dataPet) {
		this.dataPet = dataPet;
	}

}
