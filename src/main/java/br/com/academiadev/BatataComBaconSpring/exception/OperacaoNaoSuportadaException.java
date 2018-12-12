package br.com.academiadev.BatataComBaconSpring.exception;

public class OperacaoNaoSuportadaException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OperacaoNaoSuportadaException(String message) {
		super(message);
	}

}
