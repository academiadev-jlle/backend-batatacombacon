package br.com.academiadev.BatataComBaconSpring.model;


import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.academiadev.BatataComBaconSpring.enums.Objetivo;
import br.com.academiadev.BatataComBaconSpring.enums.Porte;
import br.com.academiadev.BatataComBaconSpring.enums.Sexo;
import br.com.academiadev.BatataComBaconSpring.enums.Especie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Pet extends AuditEntity<Long> {

	@NotBlank
	@Size(min = 3, max = 30)
	private String nome;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Porte porte;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Especie especie;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Objetivo objetivo;

	@NotBlank
	private String localPet;
	
	@NotNull
	@ManyToOne
	private User usuario;
}