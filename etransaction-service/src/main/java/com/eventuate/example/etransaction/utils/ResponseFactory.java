package com.eventuate.example.etransaction.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.eventuate.example.constant.MessageConstant;
import com.eventuate.example.constant.MessageEnum;
import com.eventuate.example.info.GeneralResponse;
import com.eventuate.example.info.ResponseStatus;


@SuppressWarnings("rawtypes")
public class ResponseFactory {


	private ResponseFactory() {
		throw new IllegalStateException("Utility class");
	}

	public static ResponseEntity success() {
		GeneralResponse<Object> responseObject = new GeneralResponse<>();
		responseObject.setStatus(getResponseStatus(MessageConstant.SUCCESS));
		return ResponseEntity.ok(responseObject);
	}

	public static ResponseEntity success(Object data, Class<?> clazz) {
		GeneralResponse<Object> responseObject = new GeneralResponse<>();
		responseObject.setStatus(getResponseStatus(MessageConstant.SUCCESS));
		responseObject.setData(clazz.cast(data));
		return ResponseEntity.ok(responseObject);
	}
	
	public static ResponseEntity success(Object data, Class<?> clazz, ResponseStatus status) {
		GeneralResponse<Object> responseObject = new GeneralResponse<>();
		responseObject.setStatus(status);
		responseObject.setData(clazz.cast(data));
		return ResponseEntity.ok(responseObject);
	}

    public static ResponseEntity error(HttpStatus httpStatus, String code) {
        return error(httpStatus, code, null);
    }

	@SuppressWarnings("unchecked")
	public static ResponseEntity error(HttpStatus httpStatus, String code, Object data) {
        GeneralResponse responseObject = new GeneralResponse();
        responseObject.setStatus(getResponseStatus(code));
        responseObject.setData(data);
        return new ResponseEntity<>(responseObject, httpStatus);
    }

	public static ResponseEntity error(HttpStatus httpStatus, MessageEnum responseStatus) {
		GeneralResponse responseObject = new GeneralResponse();
		responseObject.setStatus(getResponseStatus(responseStatus));
		return new ResponseEntity<>(responseObject, httpStatus);
	}

	private static ResponseStatus getResponseStatus(MessageEnum responseStatus) {
		return new ResponseStatus(responseStatus.getCode(), responseStatus.getMessage());
	}

	private static ResponseStatus getResponseStatus(String code) {
        return new ResponseStatus(code, code);
    }


}
