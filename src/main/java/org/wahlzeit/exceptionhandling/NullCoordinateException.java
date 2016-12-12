package org.wahlzeit.exceptionhandling;

public class NullCoordinateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3661407048184192739L;

	/**
	 * 
	 */
	public NullCoordinateException () {
		super();
	}
	
	/**
	 * 
	 * @param exceptionMsg
	 */
	public NullCoordinateException (String exceptionMsg) {
		super(exceptionMsg);
	}
	
	/**
	 * 
	 * @param exceptionMsg
	 * @param throwable
	 */
	public NullCoordinateException (String exceptionMsg, Throwable throwable) {
		super(exceptionMsg, throwable);
	}
	
	
}
