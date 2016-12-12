package org.wahlzeit.exceptionhandling;

public class IllegalParameterDistanceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2355846555253112075L;
	
	/**
	 * 
	 */
	public IllegalParameterDistanceException () {
		super();
	}
	
	/**
	 * 
	 * @param exceptionMsg
	 */
	public IllegalParameterDistanceException (String exceptionMsg) {
		super(exceptionMsg);
	}
	
	/**
	 * 
	 * @param exceptionMsg
	 * @param throwable
	 */
	public IllegalParameterDistanceException (String exceptionMsg, Throwable throwable) {
		super(exceptionMsg, throwable);
	}
	
}
