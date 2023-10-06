package com.udemy.best_travel.infraestructure.services;

import com.udemy.best_travel.api.models.request.ReservationRequest;
import com.udemy.best_travel.api.models.response.HotelResponse;
import com.udemy.best_travel.api.models.response.ReservationResponse;
import com.udemy.best_travel.domain.entities.ReservationEntity;
import com.udemy.best_travel.domain.repositories.CustomerRepository;
import com.udemy.best_travel.domain.repositories.HotelRepository;
import com.udemy.best_travel.domain.repositories.ReservationRepository;
import com.udemy.best_travel.infraestructure.abstract_services.IReservationService;
import com.udemy.best_travel.infraestructure.helpers.CustomerHelper;
import com.udemy.best_travel.util.exceptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class ReservationService implements IReservationService {

    private final HotelRepository hotelRepository;
    private final CustomerRepository customerRepository;
    private final ReservationRepository reservationRepository;
    private final CustomerHelper customerHelper;

    private static final String EXCEPTION_HOTEL = "hotel";
    private static final String EXCEPTION_CUSTOMER = "customer";
    private static final String EXCEPTION_RESERVATION = "reservation";

    public static final BigDecimal charges_price_percentage = BigDecimal.valueOf(0.20);

    @Override
    public ReservationResponse create(ReservationRequest request) {
        var hotel = this.hotelRepository.findById(request.getIdHotel())
                .orElseThrow(() -> new IdNotFoundException(EXCEPTION_HOTEL));
        var customer = this.customerRepository.findById(request.getIdClient())
                .orElseThrow(() -> new IdNotFoundException(EXCEPTION_CUSTOMER));

        var reservationToPersist = ReservationEntity.builder()
                .id(UUID.randomUUID())
                .hotel(hotel)
                .customer(customer)
                .totalDays(request.getTotalDays())
                .dateTimeReservation(LocalDateTime.now())
                .dateStart(LocalDate.now())
                .dateEnd(LocalDate.now().plusDays(request.getTotalDays()))
                .price(hotel.getPrice().add(hotel.getPrice().multiply(charges_price_percentage)))
                .build();

        var reservationPersisted = reservationRepository.save(reservationToPersist);

        this.customerHelper.incrase(customer.getDni(), ReservationService.class);

        return this.entityToResponse(reservationPersisted);
    }

    @Override
    public ReservationResponse read(UUID id) {
        var reservationFromDB = this.reservationRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(EXCEPTION_RESERVATION));
        return this.entityToResponse(reservationFromDB);
    }

    @Override
    public ReservationResponse update(ReservationRequest request, UUID id) {
        var hotel = this.hotelRepository.findById(request.getIdHotel())
                .orElseThrow(() -> new IdNotFoundException(EXCEPTION_HOTEL));

        var reservationToUpdate = this.reservationRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(EXCEPTION_RESERVATION));

        reservationToUpdate.setHotel(hotel);
        reservationToUpdate.setTotalDays(request.getTotalDays());
        reservationToUpdate.setDateTimeReservation(LocalDateTime.now());
        reservationToUpdate.setDateStart(LocalDate.now());
        reservationToUpdate.setDateEnd(LocalDate.now().plusDays(request.getTotalDays()));
        reservationToUpdate.setPrice(hotel.getPrice().add(hotel.getPrice().multiply(charges_price_percentage)));

        var reservationUpdated = this.reservationRepository.save(reservationToUpdate);
        log.info("Reservation updated with id {}", reservationUpdated.getId());

        return this.entityToResponse(reservationUpdated);
    }

    @Override
    public void delete(UUID id) {
        var reservationToDelete = reservationRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(EXCEPTION_RESERVATION));
        this.reservationRepository.delete(reservationToDelete);
    }

    @Override
    public BigDecimal findPrice(Long hotelId) {
        var hotel = this.hotelRepository.findById(hotelId).orElseThrow(() -> new IdNotFoundException(EXCEPTION_HOTEL));
        return hotel.getPrice().add(hotel.getPrice().multiply(charges_price_percentage));
    }

    private ReservationResponse entityToResponse(ReservationEntity entity) {
        var response = new ReservationResponse();
        BeanUtils.copyProperties(entity, response);
        var hotelResponse = new HotelResponse();
        BeanUtils.copyProperties(entity.getHotel(), hotelResponse);
        response.setHotel(hotelResponse);
        return response;
    }
}
