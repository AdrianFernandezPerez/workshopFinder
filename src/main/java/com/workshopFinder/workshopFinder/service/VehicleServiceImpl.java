package com.workshopFinder.workshopFinder.service;

import com.workshopFinder.workshopFinder.model.User;
import com.workshopFinder.workshopFinder.model.Vehicle;
import com.workshopFinder.workshopFinder.model.VehicleType;
import com.workshopFinder.workshopFinder.repository.UserRepository;
import com.workshopFinder.workshopFinder.repository.VehicleRepository;
import com.workshopFinder.workshopFinder.service.exception.UserServiceException;
import com.workshopFinder.workshopFinder.service.exception.VehicleServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleServiceImpl {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Transactional
    public void addVehicle(Long idUser, Vehicle vehicle) {
        User user = userRepository.findById(idUser).orElse(null);
        if (user == null) {
            throw new UserServiceException("No existe usuario con id " + idUser);
        }
        user.addVehiculos(vehicle);
        vehicleRepository.save(vehicle);
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
