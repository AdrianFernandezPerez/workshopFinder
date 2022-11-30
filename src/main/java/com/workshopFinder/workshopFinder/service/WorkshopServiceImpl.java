package com.workshopFinder.workshopFinder.service;

import com.workshopFinder.workshopFinder.model.Vehicle;
import com.workshopFinder.workshopFinder.model.VehicleType;
import com.workshopFinder.workshopFinder.model.Workshop;
import com.workshopFinder.workshopFinder.repository.VehicleRepository;
import com.workshopFinder.workshopFinder.repository.WorkshopRepository;
import com.workshopFinder.workshopFinder.service.exception.VehicleServiceException;
import com.workshopFinder.workshopFinder.service.exception.WorkshopServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkshopServiceImpl {

    private WorkshopRepository workshopRepository;

    @Autowired
    public WorkshopServiceImpl(WorkshopRepository workshopRepository) {
        this.workshopRepository = workshopRepository;
    }

    @Transactional
    public Workshop newWorkshop(String nombre, Integer telefono, String email, String direccion) {
        Workshop workshop = new Workshop(nombre, telefono, email, direccion);
        workshopRepository.save(workshop);
        return workshop;
    }

    @Transactional(readOnly = true)
    public List<Workshop> findAll() {
        List<Workshop> workshops = new ArrayList<>();
        workshopRepository.findAll().forEach(workshops::add);
        return workshops;
    }

    @Transactional(readOnly = true)
    public Workshop findById(Long workshopId) {
        return workshopRepository.findById(workshopId).orElse(null);
    }

    @Transactional
    public Workshop updateWorkshop(Long idWorkshop, String nombre, Integer telefono, String email, String direccion) {
        Workshop workshop = workshopRepository.findById(idWorkshop).orElse(null);
        if (workshop == null) {
            throw new WorkshopServiceException("No existe el taller con id " + idWorkshop);
        }
        workshop.setNombre(nombre);
        workshop.setTelefono(telefono);
        workshop.setEmail(email);
        workshop.setDireccion(direccion);

        workshopRepository.save(workshop);
        return workshop;
    }

    @Transactional
    public void deleteWorkshop(Long idWorkshop) {
        Workshop workshop = workshopRepository.findById(idWorkshop).orElse(null);
        if (workshop == null) {
            throw new WorkshopServiceException("No existe el taller con id " + idWorkshop);
        }
        workshopRepository.delete(workshop);
    }

}
