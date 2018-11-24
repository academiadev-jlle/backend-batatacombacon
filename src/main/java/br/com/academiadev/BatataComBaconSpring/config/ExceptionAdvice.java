package br.com.academiadev.BatataComBaconSpring.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.academiadev.BatataComBaconSpring.exception.PetNaoEncontradoException;
import br.com.academiadev.BatataComBaconSpring.exception.UserNaoEncontradoException;

@RestControllerAdvice
public class ExceptionAdvice {
	
	@ExceptionHandler(PetNaoEncontradoException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ExceptionResponse handlePetNaoEncontradoException(PetNaoEncontradoException ex) {
		return new ExceptionResponse(HttpStatus.NOT_FOUND, ex.getMessage());
	}
	
	@ExceptionHandler(UserNaoEncontradoException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ExceptionResponse handleUserNaoEncontradoException(UserNaoEncontradoException ex) {
		return new ExceptionResponse(HttpStatus.NOT_FOUND, ex.getMessage());
	}

}
