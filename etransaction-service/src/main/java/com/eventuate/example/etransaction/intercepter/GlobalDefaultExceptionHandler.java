package com.eventuate.example.etransaction.intercepter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eventuate.example.constant.MessageConstant;
import com.eventuate.example.etransaction.utils.ResponseFactory;
import com.eventuate.example.exception.ApplicationException;
import com.eventuate.example.exception.BusinessLogicException;
import com.eventuate.example.exception.ObjectNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity defaultExceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseFactory.error(HttpStatus.INTERNAL_SERVER_ERROR, MessageConstant.GENERAL_ERROR);
    }

    @ExceptionHandler(value = ApplicationException.class)
    @ResponseBody
    public ResponseEntity applicationExceptionHandler(ApplicationException e) {
        log.error(e.getMessage(), e);
        return ResponseFactory.error(HttpStatus.INTERNAL_SERVER_ERROR, MessageConstant.GENERAL_ERROR);
    }

    @ExceptionHandler(value = BusinessLogicException.class)
    @ResponseBody
    public ResponseEntity businessLogicExceptionHandler(BusinessLogicException e) {
        return ResponseFactory.error(HttpStatus.BAD_REQUEST, e.getCode());
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity defaultHttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        log.debug(e.getMessage());
        return ResponseFactory.error(HttpStatus.BAD_REQUEST, MessageConstant.BAD_REQUEST);
    }

   
    @ExceptionHandler(value = ObjectNotFoundException.class)
    @ResponseBody
    public ResponseEntity objectNotFoundExceptionHandler(ObjectNotFoundException e) {
        log.error(e.getMessage(), e);
        return ResponseFactory.error(HttpStatus.OK, e.getCode());
    }


}