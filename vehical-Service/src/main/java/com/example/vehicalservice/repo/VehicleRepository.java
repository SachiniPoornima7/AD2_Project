package com.example.vehicalservice.repo;


import com.example.vehicalservice.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByLicensePlate(String licensePlate);

    Optional<Vehicle> findById(Long id);
}
