package com.ridelink.ridelink.service.rideService;

import com.ridelink.ridelink.dto.rideDto.CreateRideRequestDTO;
import com.ridelink.ridelink.dto.rideDto.RideResponseDTO;
import com.ridelink.ridelink.dto.rideDto.UpdateRideRequestDTO;
import com.ridelink.ridelink.dto.rideSearchDto.RideSearchRequestDTO;
import com.ridelink.ridelink.dto.rideSearchDto.RideSearchResponseDTO;
import com.ridelink.ridelink.entity.Ride;

import java.util.List;

public interface RideService {

    RideResponseDTO createRide(CreateRideRequestDTO request);

    List<RideResponseDTO> getMyRides();

    RideResponseDTO getRideById(Long rideId);

    RideResponseDTO updateRide(Long rideId, UpdateRideRequestDTO request);

    void deleteRide(Long rideId);
    List<RideSearchResponseDTO> searchRides(RideSearchRequestDTO searchQuery);
}
