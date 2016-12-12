package org.wahlzeit.exceptionhandling;

public class DoubleOutOfRangeException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3485067988507456200L;

	/**
	 * 
	 */
	public DoubleOutOfRangeException () {
		super();
	}
	
	/**
	 * 
	 * @param exceptionMsg
	 */
	public DoubleOutOfRangeException (String exceptionMsg) {
		super(exceptionMsg);
	}
	
	/**
	 * 
	 * @param exceptionMsg
	 * @param throwable
	 */
	public DoubleOutOfRangeException (String exceptionMsg, Throwable throwable) {
		super(exceptionMsg, throwable);
	}
}
