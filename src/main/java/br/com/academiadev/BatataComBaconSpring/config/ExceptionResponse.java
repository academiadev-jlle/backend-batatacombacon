package br.com.academiadev.BatataComBaconSpring.config;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExceptionResponse {
	
	private HttpStatus status;
	
	private String message;

}
