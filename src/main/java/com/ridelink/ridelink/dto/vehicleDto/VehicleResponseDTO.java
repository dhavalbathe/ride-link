package com.ridelink.ridelink.dto.vehicleDto;

import com.ridelink.ridelink.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponseDTO {

    private Long id;

    private String vehicleNumber;

    private VehicleType vehicleType;

    private String brand;

    private String model;

    private String color;

    private Integer seatCapacity;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
