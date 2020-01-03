package com.eventuate.example.exception;


public class BusinessLogicException extends ApplicationException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 640931395366245990L;

	public BusinessLogicException(String code) {
        super(code);
    }

    public BusinessLogicException(String code, String message) {
        super(code, message);
    }
}
