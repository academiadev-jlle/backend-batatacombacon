package br.com.academiadev.BatataComBaconSpring.exception;

public class PetNaoEncontradoException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PetNaoEncontradoException(String message) {
		super(message);
	}

}
