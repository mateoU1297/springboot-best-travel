package com.udemy.best_travel.infraestructure.services;

import com.udemy.best_travel.api.models.request.ReservationRequest;
import com.udemy.best_travel.api.models.response.ReservationResponse;
import com.udemy.best_travel.infraestructure.abstract_services.IReservationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class ReservationService implements IReservationService {
    @Override
    public ReservationResponse create(ReservationRequest request) {
        return null;
    }

    @Override
    public ReservationResponse read(UUID uuid) {
        return null;
    }

    @Override
    public ReservationResponse update(ReservationRequest request, UUID uuid) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {

    }
}
