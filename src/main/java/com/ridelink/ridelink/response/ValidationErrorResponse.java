package com.ridelink.ridelink.response;

import com.ridelink.ridelink.enums.ErrorCode;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidationErrorResponse {
    private boolean success;

    private String message;

    private ErrorCode errorCode;

    private Map<String, String> errors;

    private LocalDateTime timestamp;

    public static ValidationErrorResponse failure(String message, ErrorCode errorCode, Map<String, String> errors) {
        return ValidationErrorResponse.builder()
                .success(false)
                .message(message)
                .errorCode(errorCode)
                .errors(errors)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
