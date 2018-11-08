package br.com.academiadev.BatataComBaconSpring.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@ApiModel(description = "Usuário")
public class User {

	@Id
	@GeneratedValue
	@ApiModelProperty(example = "1", name = "Identificador")
	private Long id;

	@NotBlank(message = "Usuário precisa de um nome válido")
	@Size(min = 3, max = 120)
	@ApiModelProperty(example = "João da Silva", name = "Nome")
	private String nome;

	@NotBlank
	@Email
	@ApiModelProperty(example = "exemplo@exemplo.com", name = "Email")
	private String email;

	@NotBlank
	@Size(min = 6, max = 30)
	@ApiModelProperty(example = "SeperSecreto", name = "Senha")
	private String senha;

	@CreatedDate
	@Column(name = "created_at", updatable = false)
	@ApiModelProperty(hidden = true)
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Column(name = "updated_at", updatable = true)
	@ApiModelProperty(hidden = true)
	private LocalDateTime updatedAt;
}
