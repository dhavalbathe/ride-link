package com.ridelink.ridelink.dto.rideDto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRideRequestDTO {


    @NotNull(message = "Vehicle id is required")
    private Long vehicleId;


    @NotBlank(message = "Source location is required")
    @Size(
            min = 3,
            max = 100,
            message = "Source must be between 3 and 100 characters"
    )
    private String source;


    @NotBlank(message = "Destination location is required")
    @Size(
            min = 3,
            max = 100,
            message = "Destination must be between 3 and 100 characters"
    )
    private String destination;


    @NotNull(message = "Departure time is required")
    @Future(
            message = "Departure time must be in the future"
    )
    private LocalDateTime departureTime;


    @NotNull(message = "Estimated arrival time is required")
    @Future(
            message = "Estimated arrival time must be in the future"
    )
    private LocalDateTime estimatedArrivalTime;


    @NotNull(message = "Price per seat is required")
    @DecimalMin(
            value = "0.0",
            inclusive = false,
            message = "Price per seat must be greater than zero"
    )
    private BigDecimal pricePerSeat;


    @Size(
            max = 500,
            message = "Description cannot exceed 500 characters"
    )
    private String description;
}
