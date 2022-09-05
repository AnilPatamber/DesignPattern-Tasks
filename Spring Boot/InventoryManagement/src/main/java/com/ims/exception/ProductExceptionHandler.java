package com.ims.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler {
    @ExceptionHandler(value = ItemNotFoundException.class)
    public ResponseEntity<Object> exception(ItemNotFoundException exception){
        return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
    }
}
