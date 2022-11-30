package com.workshopFinder.workshopFinder.repository;

import com.workshopFinder.workshopFinder.model.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkshopRepository extends JpaRepository<Workshop, Long> {
}
