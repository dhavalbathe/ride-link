package com.ridelink.ridelink.repository;

import com.ridelink.ridelink.entity.Ride;
import com.ridelink.ridelink.entity.User;
import com.ridelink.ridelink.entity.Vehicle;
import com.ridelink.ridelink.enums.RideStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query("""
            SELECT r
            FROM Ride r
            WHERE LOWER(r.source) = LOWER(:source)
            AND LOWER(r.destination) = LOWER(:destination)
            AND r.availableSeats >= :requiredSeats
            AND r.departureTime BETWEEN :startOfDay AND :endOfDay
            """)
    List<Ride> searchRides(
            @Param("source") String source,
            @Param("destination") String destination,
            @Param("requiredSeats") Integer requiredSeats,
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay
    );
}