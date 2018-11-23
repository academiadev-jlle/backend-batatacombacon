package br.com.academiadev.BatataComBaconSpring.model;


import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User extends AuditEntity<Long> {

	@NotBlank(message = "Usuário precisa de um nome válido")
	@Size(min = 3, max = 120)
	private String nome;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Size(min = 6, max = 30)
	private String senha;

	public User(Long id) {
		this.id = id;
	}
}
