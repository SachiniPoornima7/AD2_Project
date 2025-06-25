package com.example.vehicalservice.service.impl;

import com.example.vehicalservice.dto.VehicleDTO;
import com.example.vehicalservice.entity.Vehicle;
import com.example.vehicalservice.repo.VehicleRepository;
import com.example.vehicalservice.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String registerVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = modelMapper.map(vehicleDTO, Vehicle.class);
        vehicleRepository.save(vehicle);
        return "Vehicle registered successfully with ID: " + vehicle.getVehicleId();
    }

    @Override
    public VehicleDTO getVehicleById(Long vehicleId) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);
        return vehicle.map(v -> modelMapper.map(v, VehicleDTO.class)).orElse(null);
    }

    @Override
    public VehicleDTO getVehicleByLicensePlate(String licensePlate) {
        Optional<Vehicle> vehicle = vehicleRepository.findByLicensePlate(licensePlate);
        return vehicle.map(v -> modelMapper.map(v, VehicleDTO.class)).orElse(null);
    }

    @Override
    public VehicleDTO updateVehicle(VehicleDTO vehicleDTO) {
        if (vehicleRepository.existsById(vehicleDTO.getVehicleId())) {
            Vehicle vehicle = modelMapper.map(vehicleDTO, Vehicle.class);
            vehicleRepository.save(vehicle);
            return vehicleDTO;
        }
        return null;
    }

    @Override
    public Boolean deleteVehicle(Long vehicleId) {
        if (vehicleRepository.existsById(vehicleId)) {
            vehicleRepository.deleteById(vehicleId);
            return true;
        }
        return false;
    }

    @Override
    public List<VehicleDTO> getVehiclesByUser(Long id) {
        Optional<Vehicle> vehicles = vehicleRepository.findById(id);
        return vehicles.stream()
                .map(vehicle -> modelMapper.map(vehicle, VehicleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public String updateParkingStatus(Long id, boolean isParked) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        if (optionalVehicle.isPresent()) {
            Vehicle vehicle = optionalVehicle.get();
            vehicle.isParked(isParked);
            vehicleRepository.save(vehicle);
            return "Parking status updated to: " + isParked;
        }
        return "Vehicle not found";
    }
}