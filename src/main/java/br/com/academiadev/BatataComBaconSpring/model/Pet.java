package br.com.academiadev.BatataComBaconSpring.model;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.academiadev.BatataComBaconSpring.enums.Objetivo;
import br.com.academiadev.BatataComBaconSpring.enums.Raca;

@Entity
public class Pet {
	@Id
	@GeneratedValue
	private Long id;
//	@ManyToOne(optional = false)
	@NotNull
	private Long idUsuario;
	@NotNull
	@Size(min = 3, max = 30)
	private String nome;
	@Enumerated(EnumType.STRING)
	private Raca raca;
	@Enumerated(EnumType.STRING)
	private Objetivo objetivo;
	private LocalDate dataCriacao;
	private LocalDate dataEncontrado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Raca getRaca() {
		return raca;
	}

	public void setRaca(Raca raca) {
		this.raca = raca;
	}

	public Objetivo getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(Objetivo objetivo) {
		this.objetivo = objetivo;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Optional<LocalDate> getDataEncontrado() {
		return Optional.ofNullable(dataEncontrado);
	}

	public void setDataEncontrado(LocalDate dataEncontrado) {
		this.dataEncontrado = dataEncontrado;
	}
}
