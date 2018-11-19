package br.com.academiadev.BatataComBaconSpring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(example = "1" ,name = "Identificador do usuário")
	private Long id;
	@NotNull(message = "Usuário precisa de um nome válido")
	@Size(min = 3, max = 120)
    @ApiModelProperty(example = "José Silva Figueiredo" ,name = "Nome do usuário")
	private String nome;
	@NotNull
	@Email
    @ApiModelProperty(example = "petcodes@petcodes.com.br" ,name = "E-mail do usuário")
	private String email;
	@NotNull
	@Size(min = 6, max = 30)
    @ApiModelProperty(example = "minha senha é essa" ,name = "Senha do usuário")
	private String senha;
}
