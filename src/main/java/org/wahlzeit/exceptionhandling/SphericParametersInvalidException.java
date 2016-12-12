package org.wahlzeit.exceptionhandling;

public class SphericParametersInvalidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4120714507079909269L;

	/**
	 * 
	 */
	public SphericParametersInvalidException () {
		super();
	}
	
	/**
	 * 
	 * @param exceptionMsg
	 */
	public SphericParametersInvalidException (String exceptionMsg) {
		super(exceptionMsg);
	}
	
	/**
	 * 
	 * @param exceptionMsg
	 * @param throwable
	 */
	public SphericParametersInvalidException (String exceptionMsg, Throwable throwable) {
		super(exceptionMsg, throwable);
	}
	
}
