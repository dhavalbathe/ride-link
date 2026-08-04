package com.ridelink.ridelink.service.rideService;

import com.ridelink.ridelink.dto.rideDto.CreateRideRequestDTO;
import com.ridelink.ridelink.dto.rideDto.RideResponseDTO;
import com.ridelink.ridelink.dto.rideDto.UpdateRideRequestDTO;

import java.util.List;

public interface RideService {

    RideResponseDTO createRide(CreateRideRequestDTO request);

    List<RideResponseDTO> getMyRides();

    RideResponseDTO getRideById(Long rideId);

    RideResponseDTO updateRide(Long rideId, UpdateRideRequestDTO request);

    void deleteRide(Long rideId);
}
