package br.com.academiadev.BatataComBaconSpring.dto.request;


import lombok.Data;

@Data
public class RequestUserDTO {
	
	private Long id;
	
	private String nome;
	
	private String descricao;

	private String email;
	
	private String created_at;
	
}
