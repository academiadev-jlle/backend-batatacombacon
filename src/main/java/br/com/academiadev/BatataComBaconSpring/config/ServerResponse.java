package br.com.academiadev.BatataComBaconSpring.config;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ServerResponse {
	
	private HttpStatus status;
	
	private String message;

}
