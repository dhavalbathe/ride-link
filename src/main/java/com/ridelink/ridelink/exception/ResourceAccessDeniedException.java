package com.ridelink.ridelink.exception;

public class ResourceAccessDeniedException extends RuntimeException{
    public ResourceAccessDeniedException(String message) {
        super(message);
    }
}
