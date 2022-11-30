package com.workshopFinder.workshopFinder.service.db;


import com.workshopFinder.workshopFinder.model.Vehicle;
import com.workshopFinder.workshopFinder.repository.VehicleRepository;
import com.workshopFinder.workshopFinder.service.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class VehicleServiceJpa implements IVehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public void save(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    @Override
    public void delete(Integer idVehicle) {
        vehicleRepository.deleteById(Long.valueOf(idVehicle));
    }

    @Override
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle findById(Integer idVehicle) {
        for(Vehicle vehicle : findAll()){
            if(vehicle.getIdVehiculo().equals(Long.valueOf(idVehicle))){
                return vehicle;
            }
        }
        return null;
    }

}
