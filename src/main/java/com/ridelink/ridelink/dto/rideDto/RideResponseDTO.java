package com.ridelink.ridelink.dto.rideDto;

import com.ridelink.ridelink.enums.RideStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RideResponseDTO {

    private Long id;

    private Long driverId;

    private String driverName;

    private Long vehicleId;

    private String vehicleModel;

    private String vehicleNumber;

    private String source;

    private String destination;

    private LocalDateTime departureTime;

    private LocalDateTime estimatedArrivalTime;

    private BigDecimal pricePerSeat;

    private Integer availableSeats;

    private RideStatus status;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
