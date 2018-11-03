package br.com.academiadev.BatataComBaconSpring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return nome;
	}

	public void setName(String name) {
		this.nome = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return senha;
	}

	public void setPassword(String password) {
		this.senha = password;
	}

}
