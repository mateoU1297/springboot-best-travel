package com.udemy.best_travel.infraestructure.abstract_services;

import com.udemy.best_travel.api.models.request.TourRequest;
import com.udemy.best_travel.api.models.response.TourResponse;

import java.util.UUID;

public interface ITourService extends SimpleCrudService<TourRequest, TourResponse, Long> {

    void removeTicket(UUID ticketId, Long tourId);

    UUID addTicket(Long flyId, Long tourId);

    void removeReservation(UUID reservationId, Long tourId);

    UUID addReservation(Long reservationId, Long tourId, Integer totalDays);
}
