package com.ridelink.ridelink.service.vehicleService;

import com.ridelink.ridelink.dto.vehicleDto.CreateVehicleRequestDTO;
import com.ridelink.ridelink.dto.vehicleDto.UpdateVehicleRequestDTO;
import com.ridelink.ridelink.dto.vehicleDto.VehicleResponseDTO;
import com.ridelink.ridelink.entity.User;
import com.ridelink.ridelink.entity.Vehicle;
import com.ridelink.ridelink.exception.UsernameNotFoundException;
import com.ridelink.ridelink.exception.vehicleException.VehicleAlreadyExistsException;
import com.ridelink.ridelink.exception.vehicleException.VehicleNotFoundException;
import com.ridelink.ridelink.repository.UserRepository;
import com.ridelink.ridelink.repository.VehicleRepository;
import com.ridelink.ridelink.security.UserPrinciple;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService{

    private final UserRepository userRepository;

    private final VehicleRepository vehicleRepository;

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        UserPrinciple userPrinciple =
                (UserPrinciple) authentication.getPrincipal();

        return userRepository.findById(userPrinciple.getId())
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found."));
    }

    private Vehicle getVehicle(Long vehicleId, User owner) {

        return vehicleRepository.findByIdAndOwner(vehicleId, owner)
                .orElseThrow(() ->
                        new VehicleNotFoundException("Vehicle not found."));
    }

    private VehicleResponseDTO mapToResponse(Vehicle vehicle) {

        return VehicleResponseDTO.builder()
                .id(vehicle.getId())
                .vehicleNumber(vehicle.getVehicleNumber())
                .vehicleType(vehicle.getVehicleType())
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .color(vehicle.getColor())
                .seatCapacity(vehicle.getSeatCapacity())
                .createdAt(vehicle.getCreatedAt())
                .updatedAt(vehicle.getUpdatedAt())
                .build();
    }

    @Override
    public VehicleResponseDTO addVehicle(CreateVehicleRequestDTO request) {

        User owner = getAuthenticatedUser();

        if (vehicleRepository.existsByVehicleNumber(request.getVehicleNumber())) {
            throw new VehicleAlreadyExistsException("Vehicle with this number already exists.");
        }

        Vehicle vehicle = Vehicle.builder()
                .owner(owner)
                .vehicleNumber(request.getVehicleNumber())
                .vehicleType(request.getVehicleType())
                .brand(request.getBrand())
                .model(request.getModel())
                .color(request.getColor())
                .seatCapacity(request.getSeatCapacity())
                .build();

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        return mapToResponse(savedVehicle);
    }

    @Override
    public List<VehicleResponseDTO> getMyVehicles() {

        User owner = getAuthenticatedUser();

        return vehicleRepository.findByOwner(owner)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public VehicleResponseDTO getVehicleById(Long vehicleId) {

        User owner = getAuthenticatedUser();

        Vehicle vehicle = getVehicle(vehicleId, owner);

        return mapToResponse(vehicle);
    }

    @Override
    public VehicleResponseDTO updateVehicle(Long vehicleId, UpdateVehicleRequestDTO request) {

        User owner = getAuthenticatedUser();

        Vehicle vehicle = getVehicle(vehicleId, owner);

        vehicle.setVehicleType(request.getVehicleType());
        vehicle.setBrand(request.getBrand());
        vehicle.setModel(request.getModel());
        vehicle.setColor(request.getColor());
        vehicle.setSeatCapacity(request.getSeatCapacity());

        Vehicle updatedVehicle = vehicleRepository.save(vehicle);

        return mapToResponse(updatedVehicle);
    }

    @Override
    public void deleteVehicle(Long vehicleId) {
        User owner = getAuthenticatedUser();

        Vehicle vehicle = getVehicle(vehicleId, owner);
        vehicleRepository.delete(vehicle);
    }
}
