package com.ridelink.ridelink.controller;

import com.ridelink.ridelink.dto.vehicleDto.CreateVehicleRequestDTO;
import com.ridelink.ridelink.dto.vehicleDto.UpdateVehicleRequestDTO;
import com.ridelink.ridelink.dto.vehicleDto.VehicleResponseDTO;
import com.ridelink.ridelink.response.ApiResponse;
import com.ridelink.ridelink.service.vehicleService.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<ApiResponse<VehicleResponseDTO>> addVehicle(
            @RequestBody @Valid CreateVehicleRequestDTO request) {

        VehicleResponseDTO response = vehicleService.addVehicle(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("The New Vehicle Added Successfully.", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<VehicleResponseDTO>>> getMyVehicles() {

        List<VehicleResponseDTO> response = vehicleService.getMyVehicles();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("All the Vehicle are fetched successfully.", response));
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<ApiResponse<VehicleResponseDTO>> getVehicleById(
            @PathVariable Long vehicleId) {

        VehicleResponseDTO response = vehicleService.getVehicleById(vehicleId);

        return ResponseEntity.ok(ApiResponse.success("Vehicle Fetched Successfully.", response));
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<ApiResponse<VehicleResponseDTO>> updateVehicle(
            @PathVariable Long vehicleId,
            @RequestBody @Valid UpdateVehicleRequestDTO request) {

        VehicleResponseDTO response =
                vehicleService.updateVehicle(vehicleId, request);

        return ResponseEntity.ok(ApiResponse.success("Vehicle is updated successfully.", response));
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<ApiResponse<Void>> deleteVehicle(
            @PathVariable Long vehicleId) {

        vehicleService.deleteVehicle(vehicleId);

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("The Vehicle is deleted successfully."));
    }
}