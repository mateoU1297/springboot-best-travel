package com.udemy.best_travel.domain.repositories;

import com.udemy.best_travel.domain.entities.TourEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TourRepository extends CrudRepository<TourEntity, Long> {
}
