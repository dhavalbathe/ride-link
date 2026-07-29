package com.ridelink.ridelink.repository;

import com.ridelink.ridelink.entity.User;
import com.ridelink.ridelink.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    boolean existsByVehicleNumber(String vehicleNumber);

    Optional<Vehicle> findByVehicleNumber(String vehicleNumber);

    List<Vehicle> findByOwner(User owner);

    Optional<Vehicle> findByIdAndOwner(Long id, User owner);
}
