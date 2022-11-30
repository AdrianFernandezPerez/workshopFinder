package com.workshopFinder.workshopFinder.service;

import com.workshopFinder.workshopFinder.model.Vehicle;
import com.workshopFinder.workshopFinder.model.Workshop;

import java.util.List;

public interface IWorkshopService {

    void save(Workshop workshop);

    void delete(Integer idWorkshop);

    List<Workshop> findAll();

    Workshop findById(Integer idWorkshop);

}

