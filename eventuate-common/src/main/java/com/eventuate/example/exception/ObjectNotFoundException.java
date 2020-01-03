package com.eventuate.example.exception;

import lombok.Getter;

@Getter
public class ObjectNotFoundException extends ApplicationException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6531349258292754559L;

	public ObjectNotFoundException(String code) {
        super(code);
    }

    public ObjectNotFoundException(String code, String message) {
        super(code, message);
    }
}
