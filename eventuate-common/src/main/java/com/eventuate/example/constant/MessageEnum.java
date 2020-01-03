package com.eventuate.example.constant;

import lombok.Getter;

@Getter
public enum MessageEnum {
	ERROR("error", "error"),
	SUCCESS("error", "error"),
	GENERAL_ERROR("general_error", "Any error occur"),
	BAD_REQUEST("bad_request", "Bad request"),
	INVALID_GET_BANK_ACCOUNT("error", "Error get account information from bank");

	private String code;
	private String message;

	MessageEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

}
