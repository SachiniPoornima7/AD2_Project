package com.example.vehicalservice.controller;


import com.example.vehicalservice.dto.VehicleDTO;
import com.example.vehicalservice.service.impl.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vehicle-service/api/v1/vehicle")
public class VehicleController {

    @Autowired
    private VehicleServiceImpl vehicleService;

    @PostMapping("/register")
    public ResponseEntity<String> registerVehicle(@RequestBody VehicleDTO vehicleDTO) {
        return ResponseEntity.ok(vehicleService.registerVehicle(vehicleDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable Long vehicleId) {
        VehicleDTO vehicleDTO = vehicleService.getVehicleById(vehicleId);
        return vehicleDTO != null ? ResponseEntity.ok(vehicleDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping("/license/{licensePlate}")
    public ResponseEntity<VehicleDTO> getVehicleByLicense(@PathVariable String licensePlate) {
        VehicleDTO vehicleDTO = vehicleService.getVehicleByLicensePlate(licensePlate);
        return vehicleDTO != null ? ResponseEntity.ok(vehicleDTO) : ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<VehicleDTO> updateVehicle(@RequestBody VehicleDTO vehicleDTO) {
        VehicleDTO updatedVehicle = vehicleService.updateVehicle(vehicleDTO);
        return updatedVehicle != null ? ResponseEntity.ok(updatedVehicle) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteVehicle(@PathVariable Long vehicleId) {
        return ResponseEntity.ok(vehicleService.deleteVehicle(vehicleId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<VehicleDTO>> getVehiclesByUser(@PathVariable Long userId) {
        List<VehicleDTO> vehicles = vehicleService.getVehiclesByUser(userId);
        return !vehicles.isEmpty() ? ResponseEntity.ok(vehicles) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/parking-status")
    public ResponseEntity<String> updateParkingStatus(
            @PathVariable Long vehicleId,
            @RequestParam boolean isParked) {
        return ResponseEntity.ok(vehicleService.updateParkingStatus(vehicleId, isParked));
    }
}