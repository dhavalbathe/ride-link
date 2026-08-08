package com.ridelink.ridelink.dto.rideSearchDto;

import com.ridelink.ridelink.entity.User;
import com.ridelink.ridelink.enums.VehicleType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RideSearchResponseDTO {
    private Long rideId;

    private String driverName;

    private VehicleType vehicleType;

    private String source;

    private String destination;

    private LocalDateTime departureTime;

    private LocalDateTime estimatedArrivalTime;

    private BigDecimal pricePerSeat;

    private Integer availableSeats;
}
