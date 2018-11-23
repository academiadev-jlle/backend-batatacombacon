package br.com.academiadev.BatataComBaconSpring.exception;

public class UserNaoEncontradoException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNaoEncontradoException(String message) {
		super(message);
	}

}
