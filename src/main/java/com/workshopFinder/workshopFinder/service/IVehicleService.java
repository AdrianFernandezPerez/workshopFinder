package com.workshopFinder.workshopFinder.service;

import com.workshopFinder.workshopFinder.model.User;
import com.workshopFinder.workshopFinder.model.Vehicle;

import java.util.List;

public interface IVehicleService {

    void save(Vehicle vehicle);

    void delete(Integer idVehicle);

    List<Vehicle> findAll();

    Vehicle findById(Integer idVehicle);

}

