package com.workshopFinder.workshopFinder.service;

import com.workshopFinder.workshopFinder.model.Vehicle;
import com.workshopFinder.workshopFinder.model.VehicleType;
import com.workshopFinder.workshopFinder.repository.VehicleRepository;
import com.workshopFinder.workshopFinder.service.exception.VehicleServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleServiceImpl {

    private VehicleRepository vehicleRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Transactional
    public Vehicle newVehicle(String marca, String modelo, String matricula, VehicleType tipoVehiculo) {
        Vehicle vehicle = new Vehicle(marca, modelo, matricula, tipoVehiculo);
        vehicleRepository.save(vehicle);
        return vehicle;
    }

    @Transactional(readOnly = true)
    public List<Vehicle> findAll() {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicleRepository.findAll().forEach(vehicles::add);
        return vehicles;
    }

    @Transactional(readOnly = true)
    public Vehicle findById(Long vehicleId) {
        return vehicleRepository.findById(vehicleId).orElse(null);
    }

    @Transactional
    public Vehicle updateVehicle(Long idvehiculo, String marca, String modelo, String matricula, VehicleType tipoVehiculo) {
        Vehicle vehicle = vehicleRepository.findById(idvehiculo).orElse(null);
        if (vehicle == null) {
            throw new VehicleServiceException("No existe el vehiculo con id " + idvehiculo);
        }
        vehicle.setMarca(marca);
        vehicle.setModelo(modelo);
        vehicle.setMatricula(matricula);
        vehicle.setTipoVehiculo(tipoVehiculo);

        vehicleRepository.save(vehicle);
        return vehicle;
    }

    @Transactional
    public void deleteVehicle(Long idVehiculo) {
        Vehicle vehicle = vehicleRepository.findById(idVehiculo).orElse(null);
        if (vehicle == null) {
            throw new VehicleServiceException("No existe el vehiculo con id " + idVehiculo);
        }
        vehicleRepository.delete(vehicle);
    }

}
