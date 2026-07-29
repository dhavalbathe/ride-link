package com.ridelink.ridelink.service.vehicleService;

import com.ridelink.ridelink.dto.vehicleDto.CreateVehicleRequestDTO;
import com.ridelink.ridelink.dto.vehicleDto.UpdateVehicleRequestDTO;
import com.ridelink.ridelink.dto.vehicleDto.VehicleResponseDTO;

import java.util.List;

public interface VehicleService {

    VehicleResponseDTO addVehicle(CreateVehicleRequestDTO request);

    List<VehicleResponseDTO> getMyVehicles();

    VehicleResponseDTO getVehicleById(Long vehicleId);

    VehicleResponseDTO updateVehicle(Long vehicleId, UpdateVehicleRequestDTO request);

    void deleteVehicle(Long vehicleId);
}