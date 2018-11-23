package br.com.academiadev.BatataComBaconSpring.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.academiadev.BatataComBaconSpring.exception.PetNaoEncontradoException;

@RestControllerAdvice
public class ExceptionAdvice {
	
	@ExceptionHandler(PetNaoEncontradoException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ExceptionResponse handlePetNaoEncontradoException(PetNaoEncontradoException ex) {
		return new ExceptionResponse(HttpStatus.NOT_FOUND, ex.getMessage());
	}

}
