package com.ridelink.ridelink.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExists.class)
    public ResponseEntity<String> handleEmailAlreadyExistsException(EmailAlreadyExists ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(PhoneAlreadyExists.class)
    public ResponseEntity<String> handlePhoneAlreadyExistsException(PhoneAlreadyExists ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
