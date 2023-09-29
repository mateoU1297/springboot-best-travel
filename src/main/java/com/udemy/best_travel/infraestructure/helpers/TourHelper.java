package com.udemy.best_travel.infraestructure.helpers;

import com.udemy.best_travel.domain.repositories.ReservationRepository;
import com.udemy.best_travel.domain.repositories.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
@AllArgsConstructor
public class TourHelper {

    private final TicketRepository ticketRepository;
    private final ReservationRepository reservationRepository;
}
