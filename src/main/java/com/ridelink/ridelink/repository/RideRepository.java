package com.ridelink.ridelink.repository;

import com.ridelink.ridelink.entity.Ride;
import com.ridelink.ridelink.entity.User;
import com.ridelink.ridelink.entity.Vehicle;
import com.ridelink.ridelink.enums.RideStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {

    List<Ride> findByDriver(User driver);

    List<Ride> findByVehicle(Vehicle vehicle);

    List<Ride> findByStatus(RideStatus status);

    List<Ride> findBySourceIgnoreCaseAndDestinationIgnoreCase(
            String source,
            String destination
    );

    List<Ride> findByDepartureTimeAfter(LocalDateTime departureTime);

    List<Ride> findByDriverAndStatus(
            User driver,
            RideStatus status
    );

    boolean existsByDriverAndVehicleAndDepartureTime(
            User driver,
            Vehicle vehicle,
            LocalDateTime departureTime
    );
}