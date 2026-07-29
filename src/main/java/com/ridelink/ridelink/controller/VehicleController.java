package com.ridelink.ridelink.controller;

import com.ridelink.ridelink.dto.vehicleDto.CreateVehicleRequestDTO;
import com.ridelink.ridelink.dto.vehicleDto.UpdateVehicleRequestDTO;
import com.ridelink.ridelink.dto.vehicleDto.VehicleResponseDTO;
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
    public ResponseEntity<VehicleResponseDTO> addVehicle(
            @RequestBody @Valid CreateVehicleRequestDTO request) {

        VehicleResponseDTO response = vehicleService.addVehicle(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<VehicleResponseDTO>> getMyVehicles() {

        List<VehicleResponseDTO> response = vehicleService.getMyVehicles();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<VehicleResponseDTO> getVehicleById(
            @PathVariable Long vehicleId) {

        VehicleResponseDTO response = vehicleService.getVehicleById(vehicleId);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<VehicleResponseDTO> updateVehicle(
            @PathVariable Long vehicleId,
            @RequestBody @Valid UpdateVehicleRequestDTO request) {

        VehicleResponseDTO response =
                vehicleService.updateVehicle(vehicleId, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<Void> deleteVehicle(
            @PathVariable Long vehicleId) {

        vehicleService.deleteVehicle(vehicleId);

        return ResponseEntity.noContent().build();
    }
}