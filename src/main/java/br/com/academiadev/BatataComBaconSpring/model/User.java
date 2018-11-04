package br.com.academiadev.BatataComBaconSpring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue
	private Long id;
	@NotNull(message = "Usuário precisa de um nome válido")
	@Size(min = 3, max = 120)
	private String nome;
	@NotNull
	@Email
	private String email;
	@NotNull
	@Size(min = 6, max = 30)
	private String senha;
}
