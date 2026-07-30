package com.ridelink.ridelink.dto.vehicleDto;

import com.ridelink.ridelink.enums.VehicleType;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateVehicleRequestDTO {

    @NotBlank(message = "Vehicle number is required")
    @Size(max = 10, message = "Vehicle number must not exceed 10 characters.")
    private String vehicleNumber;

    @NotNull(message = "Vehicle type is required")
    private VehicleType vehicleType;

    @NotBlank(message = "Brand is required")
    private String brand;

    @NotBlank(message = "Model is required")
    private String model;

    @NotBlank(message = "Color is required")
    private String color;

    @NotNull(message = "Seat capacity is required")
    @Min(value = 1, message = "Seat capacity must be at least 1")
    @Max(value = 10, message = "Seat capacity cannot exceed 10")
    private Integer seatCapacity;
}