package org.wahlzeit.exceptionhandling;

public class IllegalHeightMountainException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3160404687493659223L;

	/**
	 * 
	 */
	public IllegalHeightMountainException () {
		super();
	}
	
	/**
	 * 
	 * @param exceptionMsg
	 */
	public IllegalHeightMountainException (String exceptionMsg) {
		super(exceptionMsg);
	}
	
	/**
	 * 
	 * @param exceptionMsg
	 * @param throwable
	 */
	public IllegalHeightMountainException (String exceptionMsg, Throwable throwable) {
		super(exceptionMsg, throwable);
	}
}
