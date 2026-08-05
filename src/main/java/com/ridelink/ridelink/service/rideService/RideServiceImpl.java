package com.ridelink.ridelink.service.rideService;

import com.ridelink.ridelink.dto.rideDto.CreateRideRequestDTO;
import com.ridelink.ridelink.dto.rideDto.RideResponseDTO;
import com.ridelink.ridelink.dto.rideDto.UpdateRideRequestDTO;
import com.ridelink.ridelink.entity.Ride;
import com.ridelink.ridelink.entity.User;
import com.ridelink.ridelink.entity.Vehicle;
import com.ridelink.ridelink.enums.RideStatus;
import com.ridelink.ridelink.exception.ResourceAccessDeniedException;
import com.ridelink.ridelink.exception.UsernameNotFoundException;
import com.ridelink.ridelink.exception.rideException.InvalidRideException;
import com.ridelink.ridelink.exception.rideException.RideAlreadyExists;
import com.ridelink.ridelink.exception.rideException.RideNotFoundException;
import com.ridelink.ridelink.exception.vehicleException.VehicleNotFoundException;
import com.ridelink.ridelink.repository.RideRepository;
import com.ridelink.ridelink.repository.UserRepository;
import com.ridelink.ridelink.repository.VehicleRepository;
import com.ridelink.ridelink.security.UserPrinciple;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService{

    private final RideRepository rideRepository;

    private final VehicleRepository vehicleRepository;

    private final UserRepository userRepository;

    private User getAuthenticatedDriver() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return userRepository.findByEmail(userPrinciple.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }

    private Vehicle getVehicleById(Long vehicleId, User driver) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new VehicleNotFoundException("Vehicle not found."));
        if(!vehicle.getOwner().getId().equals(driver.getId())) {
            throw new ResourceAccessDeniedException(
                    "Access denied. You do not have permission to perform this action."
            );
        }
        return vehicle;
    }

    private Ride getRideByIdOrThrow(Long rideId) {

        return rideRepository.findById(rideId)
                .orElseThrow(() ->
                        new RideNotFoundException("Ride not found with id: " + rideId)
                );
    }


    private RideResponseDTO mapToResponseDTO(Ride ride) {

        return RideResponseDTO.builder()
                .id(ride.getId())
                .source(ride.getSource())
                .destination(ride.getDestination())
                .departureTime(ride.getDepartureTime())
                .availableSeats(ride.getAvailableSeats())
                .pricePerSeat(ride.getPricePerSeat())
                .driverName(ride.getDriver().getName())
                .vehicleNumber(ride.getVehicle().getVehicleNumber())
                .build();
    }


    @Override
    public RideResponseDTO createRide(CreateRideRequestDTO request) {

        User driver = getAuthenticatedDriver();

        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId()).orElseThrow(() -> new VehicleNotFoundException("Vehicle Not found"));

        if(rideRepository.existsByDriverAndVehicleAndDepartureTime(driver, vehicle, request.getDepartureTime())) {
            throw new RideAlreadyExists("A ride already exists for the selected vehicle and departure time.");
        }

        if(request.getDepartureTime().isBefore(LocalDateTime.now())) {
            throw new InvalidRideException("DepartureTime must be in the future.");
        }

        if(request.getEstimatedArrivalTime().isBefore(request.getDepartureTime())) {
            throw new InvalidRideException("Estimated arrival time must be after departure time.");
        }

        Ride ride = Ride.builder()
                .driver(driver)
                .vehicle(vehicle)
                .source(request.getSource())
                .destination(request.getDestination())
                .departureTime(request.getDepartureTime())
                .estimatedArrivalTime(request.getEstimatedArrivalTime())
                .pricePerSeat(request.getPricePerSeat())
                .availableSeats(vehicle.getSeatCapacity())
                .status(RideStatus.SCHEDULED)
                .description(request.getDescription())
                .build();

        Ride savedRide = rideRepository.save(ride);

        return mapToResponseDTO(savedRide);
    }

    @Override
    public List<RideResponseDTO> getMyRides() {
        User driver = getAuthenticatedDriver();

        return rideRepository.findByDriver(driver)
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Override
    public RideResponseDTO getRideById(Long rideId) {

        User driver = getAuthenticatedDriver();

        Ride ride = rideRepository.findById(rideId).orElseThrow(() -> new RideNotFoundException("Ride not found"));

        if (!ride.getDriver().getId().equals(driver.getId())) {
            throw new ResourceAccessDeniedException(
                    "You are not authorized to access this ride."
            );
        }
        return mapToResponseDTO(ride);
    }

    @Override
    public RideResponseDTO updateRide(Long rideId, UpdateRideRequestDTO request) {
        User driver = getAuthenticatedDriver();

        Ride ride = rideRepository.findById(rideId).orElseThrow(() -> new RideNotFoundException("Ride with this id not exists"));

        if(!ride.getDriver().getId().equals(driver.getId())) {
            throw new ResourceAccessDeniedException("You are not authorized to update this ride.");
        }

        if (request.getDepartureTime().isBefore(LocalDateTime.now())) {
            throw new InvalidRideException(
                    "Departure time must be in the future."
            );
        }

        if (request.getEstimatedArrivalTime().isBefore(request.getDepartureTime())) {
            throw new InvalidRideException(
                    "Estimated arrival time must be after departure time."
            );
        }

        if (request.getSource() != null) {
            ride.setSource(request.getSource());
        }

        if (request.getDestination() != null) {
            ride.setDestination(request.getDestination());
        }

        if (request.getPricePerSeat() != null) {
            ride.setPricePerSeat(request.getPricePerSeat());
        }

        if(request.getDepartureTime() != null) {
            ride.setDepartureTime(request.getDepartureTime());
        }

        if(request.getEstimatedArrivalTime() != null) {
            ride.setEstimatedArrivalTime(request.getEstimatedArrivalTime());
        }

        if(request.getDescription() != null) {
            ride.setDescription(request.getDescription());
        }

        Ride savedRide = rideRepository.save(ride);

        return mapToResponseDTO(savedRide);
    }

    @Override
    public void deleteRide(Long rideId) {

        User driver = getAuthenticatedDriver();

        Ride ride = getRideByIdOrThrow(rideId);

        if (!ride.getDriver().getId().equals(driver.getId())) {
            throw new ResourceAccessDeniedException(
                    "You are not authorized to delete this ride."
            );
        }

        rideRepository.delete(ride);
    }
}
