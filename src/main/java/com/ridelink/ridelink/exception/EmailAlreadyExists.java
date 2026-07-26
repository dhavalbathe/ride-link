package com.ridelink.ridelink.exception;

public class EmailAlreadyExists extends RuntimeException {
    public EmailAlreadyExists(String message) {
        super(message);
    }
}
