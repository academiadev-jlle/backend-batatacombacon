package br.com.academiadev.BatataComBaconSpring.dto.request;


import lombok.Data;

@Data
public class ResponseUserDTO {
	
	private Long id;
	
	private String nome;

	private String email;
	
	private String created_at;
	
}
