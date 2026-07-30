package com.ridelink.ridelink.response;

import com.ridelink.ridelink.enums.ErrorCode;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorResponse {
    private boolean success;

    private String message;

    private ErrorCode errorCode;

    private LocalDateTime timestamp;

    public static ApiErrorResponse failure(String message, ErrorCode errorCode) {
        return ApiErrorResponse.builder()
                .success(false)
                .message(message)
                .errorCode(errorCode)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
