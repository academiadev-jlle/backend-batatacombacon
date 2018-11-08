package br.com.academiadev.BatataComBaconSpring.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import br.com.academiadev.BatataComBaconSpring.enums.Objetivo;
import br.com.academiadev.BatataComBaconSpring.enums.Especie;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@ApiModel(description = "Pet")
public class Pet {

	@Id
	@GeneratedValue
	@ApiModelProperty(example = "1", name = "Identificador do Pet")
	private Long id;

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
	@ManyToOne
	@ApiModelProperty(hidden = true)
	@CreatedBy
	@Column(name = "created_by", updatable = false)
	private User usuario;

	@NotNull
	@ApiModelProperty(hidden = true)
	@CreatedDate
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@Nullable
	@ApiModelProperty(hidden = true)
	@Column(name = "updated_at", updatable = true)
	@LastModifiedDate
	private LocalDateTime updatedAt;
}
