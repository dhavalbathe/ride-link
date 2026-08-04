package com.ridelink.ridelink.exception;

import com.ridelink.ridelink.enums.ErrorCode;
import com.ridelink.ridelink.exception.rideException.InvalidRideException;
import com.ridelink.ridelink.exception.rideException.RideAlreadyExists;
import com.ridelink.ridelink.exception.rideException.RideNotFoundException;
import com.ridelink.ridelink.exception.vehicleException.VehicleAlreadyExistsException;
import com.ridelink.ridelink.exception.vehicleException.VehicleNotFoundException;
import com.ridelink.ridelink.response.ApiErrorResponse;
import com.ridelink.ridelink.response.ApiResponse;
import com.ridelink.ridelink.response.ValidationErrorResponse;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /* *************************************************************************
       Validation Exceptions
       **************************************************************************/

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity
                .badRequest()
                .body(
                        ValidationErrorResponse.failure(
                                "Validation failed.",
                                ErrorCode.VALIDATION_FAILED,
                                errors
                        )
                );
    }

    /* *************************************************************************
       Authentication Exceptions
       **************************************************************************/

    @ExceptionHandler(EmailAlreadyExists.class)
    public ResponseEntity<ApiErrorResponse> handleEmailAlreadyExistsException(
            EmailAlreadyExists ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        ApiErrorResponse.failure(
                                ex.getMessage(),
                                ErrorCode.EMAIL_ALREADY_EXISTS
                        )
                );
    }

    @ExceptionHandler(PhoneAlreadyExists.class)
    public ResponseEntity<ApiErrorResponse> handlePhoneAlreadyExistsException(
            PhoneAlreadyExists ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        ApiErrorResponse.failure(
                                ex.getMessage(),
                                ErrorCode.PHONE_ALREADY_EXISTS
                        )
                );
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleUsernameNotFoundException(
            UsernameNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ApiErrorResponse.failure(
                                ex.getMessage(),
                                ErrorCode.USER_NOT_FOUND
                        )
                );
    }

    /* *************************************************************************
       Vehicle Features Exceptions
       **************************************************************************/

    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleVehicleNotFoundException(
            VehicleNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ApiErrorResponse.failure(
                                ex.getMessage(),
                                ErrorCode.VEHICLE_NOT_FOUND
                        )
                );
    }

    @ExceptionHandler(VehicleAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleVehicleAlreadyExistsException(
            VehicleAlreadyExistsException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        ApiErrorResponse.failure(
                                ex.getMessage(),
                                ErrorCode.VEHICLE_ALREADY_EXISTS
                        )
                );
    }

        /* *************************************************************************
       Ride Features Exception
       **************************************************************************/

    @ExceptionHandler(RideNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleRideNotFoundException(RideNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        ApiErrorResponse.failure(
                                ex.getMessage(),
                                ErrorCode.VEHICLE_NOT_FOUND
                        )
                );
    }

    @ExceptionHandler(RideAlreadyExists.class)
    public ResponseEntity<ApiErrorResponse> handleRideAlreadyExists(RideAlreadyExists ex) {
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
                .body(
                        ApiErrorResponse.failure(
                                ex.getMessage(),
                                ErrorCode.RIDE_ALREADY_EXISTS
                        )
                );
    }

    @ExceptionHandler(InvalidRideException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidRideException(InvalidRideException ex) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(
                        ApiErrorResponse.failure(
                                ex.getMessage(),
                                ErrorCode.INVALID_RIDE
                        )
                );
    }

    /* *************************************************************************
       Generic Exception
       **************************************************************************/

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGenericException(
            Exception ex) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ApiErrorResponse.failure(
                                "Something went wrong. Please try again later.",
                                ErrorCode.INTERNAL_SERVER_ERROR
                        )
                );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ApiErrorResponse.failure(
                                "Invalid request body. Please check the request payload.",
                                        ErrorCode.INVALID_REQUEST_BODY
                        )
                );
    }

    @ExceptionHandler(ResourceAccessDeniedException.class)
    public ResponseEntity<ApiErrorResponse> handleResourceAccessDeniedException(
            ResourceAccessDeniedException ex
    ) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(
                        ApiErrorResponse.failure(
                                ex.getMessage(),
                                ErrorCode.ACCESS_DENIED
                        )
                );
    }
}