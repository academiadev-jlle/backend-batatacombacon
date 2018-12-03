package br.com.academiadev.BatataComBaconSpring.dto.post;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("Reset de Senha")
public class PasswordDTO {

	@NotBlank
	@Size(min = 6, max = 30)
	private String senha;

}
