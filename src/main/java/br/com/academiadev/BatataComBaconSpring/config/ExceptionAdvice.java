package br.com.academiadev.BatataComBaconSpring.config;

import java.io.IOException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.academiadev.BatataComBaconSpring.exception.ImagemNaoEncontradaException;
import br.com.academiadev.BatataComBaconSpring.exception.OperacaoNaoSuportadaException;
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
	
	@ExceptionHandler(IOException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ExceptionResponse handleIOException(IOException ex) {
		return new ExceptionResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
	}
	
	@ExceptionHandler(ImagemNaoEncontradaException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ExceptionResponse handleImagemNaoEncontradaException(ImagemNaoEncontradaException ex) {
		return new ExceptionResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
	}
	
	@ExceptionHandler(OperacaoNaoSuportadaException.class)
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public ExceptionResponse handleOperacaoNaoSuportadaException(OperacaoNaoSuportadaException ex) {
		return new ExceptionResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ExceptionResponse handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
		return new ExceptionResponse(HttpStatus.BAD_REQUEST, "Email já cadastrado");
	}

}
