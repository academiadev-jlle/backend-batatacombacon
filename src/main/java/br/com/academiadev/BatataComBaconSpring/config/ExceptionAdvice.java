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
	public ServerResponse handlePetNaoEncontradoException(PetNaoEncontradoException ex) {
		return new ServerResponse(HttpStatus.NOT_FOUND, ex.getMessage());
	}

	@ExceptionHandler(UserNaoEncontradoException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ServerResponse handleUserNaoEncontradoException(UserNaoEncontradoException ex) {
		return new ServerResponse(HttpStatus.NOT_FOUND, ex.getMessage());
	}

	@ExceptionHandler(IOException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ServerResponse handleIOException(IOException ex) {
		return new ServerResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
	}

	@ExceptionHandler(ImagemNaoEncontradaException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ServerResponse handleImagemNaoEncontradaException(ImagemNaoEncontradaException ex) {
		return new ServerResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
	}

	@ExceptionHandler(OperacaoNaoSuportadaException.class)
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public ServerResponse handleOperacaoNaoSuportadaException(OperacaoNaoSuportadaException ex) {
		return new ServerResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ServerResponse handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
		return new ServerResponse(HttpStatus.BAD_REQUEST, "Email já cadastrado");
	}
}
