package com.ridelink.ridelink.controller;

import com.ridelink.ridelink.dto.rideDto.CreateRideRequestDTO;
import com.ridelink.ridelink.dto.rideDto.RideResponseDTO;
import com.ridelink.ridelink.dto.rideDto.UpdateRideRequestDTO;
import com.ridelink.ridelink.dto.rideSearchDto.RideSearchRequestDTO;
import com.ridelink.ridelink.dto.rideSearchDto.RideSearchResponseDTO;
import com.ridelink.ridelink.response.ApiResponse;
import com.ridelink.ridelink.service.rideService.RideService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rides")
@RequiredArgsConstructor
public class RideController {

    private final RideService rideService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<RideSearchResponseDTO>>> getSearchRides(@ModelAttribute @Valid RideSearchRequestDTO searchQuery) {
        List<RideSearchResponseDTO> response = rideService.searchRides(searchQuery);
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        ApiResponse.success(
                                "All the Rides of your choice are fetched.",
                                    response
                        )
                );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RideResponseDTO>> createRide(
            @RequestBody @Valid CreateRideRequestDTO request) {

        RideResponseDTO response = rideService.createRide(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Ride created successfully.",
                        response
                ));
    }

    @GetMapping("/my")
    public ResponseEntity<ApiResponse<List<RideResponseDTO>>> getMyRides() {

        List<RideResponseDTO> response = rideService.getMyRides();

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Rides fetched successfully.",
                        response
                )
        );
    }

    @GetMapping("/{rideId}")
    public ResponseEntity<ApiResponse<RideResponseDTO>> getRideById(
            @PathVariable Long rideId) {

        RideResponseDTO response = rideService.getRideById(rideId);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Ride fetched successfully.",
                        response
                )
        );
    }

    @PutMapping("/{rideId}")
    public ResponseEntity<ApiResponse<RideResponseDTO>> updateRide(
            @PathVariable Long rideId,
            @RequestBody @Valid UpdateRideRequestDTO request) {

        RideResponseDTO response =
                rideService.updateRide(rideId, request);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Ride updated successfully.",
                        response
                )
        );
    }

    @DeleteMapping("/{rideId}")
    public ResponseEntity<ApiResponse<Void>> deleteRide(
            @PathVariable Long rideId) {

        rideService.deleteRide(rideId);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Ride deleted successfully."
                )
        );
    }
}