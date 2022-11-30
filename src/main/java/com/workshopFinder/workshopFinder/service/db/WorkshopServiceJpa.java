package com.workshopFinder.workshopFinder.service.db;

import com.workshopFinder.workshopFinder.model.Vehicle;
import com.workshopFinder.workshopFinder.model.Workshop;
import com.workshopFinder.workshopFinder.repository.VehicleRepository;
import com.workshopFinder.workshopFinder.repository.WorkshopRepository;
import com.workshopFinder.workshopFinder.service.IWorkshopService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WorkshopServiceJpa implements IWorkshopService {
    @Autowired
    private WorkshopRepository workshopRepository;

    @Override
    public void save(Workshop workshop) {
        workshopRepository.save(workshop);
    }

    @Override
    public void delete(Integer idWorkshop) {
        workshopRepository.deleteById(Long.valueOf(idWorkshop));
    }

    @Override
    public List<Workshop> findAll() {
        return workshopRepository.findAll();
    }

    @Override
    public Workshop findById(Integer idWorkshop) {
        for (Workshop workshop : findAll()) {
            if (workshop.get_id().equals(Long.valueOf(idWorkshop))) {
                return workshop;
            }
        }
        return null;
    }
}
