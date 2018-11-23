package br.com.academiadev.BatataComBaconSpring.dto.post;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Usuário")
public class PostUserDTO {
	
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

}
