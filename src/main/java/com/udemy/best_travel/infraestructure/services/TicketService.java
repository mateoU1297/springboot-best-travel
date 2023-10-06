package com.udemy.best_travel.infraestructure.services;

import com.udemy.best_travel.api.models.request.TicketRequest;
import com.udemy.best_travel.api.models.response.FlyResponse;
import com.udemy.best_travel.api.models.response.TicketResponse;
import com.udemy.best_travel.domain.entities.TicketEntity;
import com.udemy.best_travel.domain.repositories.CustomerRepository;
import com.udemy.best_travel.domain.repositories.FlyRepository;
import com.udemy.best_travel.domain.repositories.TicketRepository;
import com.udemy.best_travel.infraestructure.abstract_services.ITicketService;
import com.udemy.best_travel.infraestructure.helpers.BlackListHelper;
import com.udemy.best_travel.infraestructure.helpers.CustomerHelper;
import com.udemy.best_travel.util.BestTravelUtil;
import com.udemy.best_travel.util.enums.Tables;
import com.udemy.best_travel.util.exceptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class TicketService implements ITicketService {

    private final FlyRepository flyRepository;
    private final CustomerRepository customerRepository;
    private final TicketRepository ticketRepository;
    private final CustomerHelper customerHelper;
    private final BlackListHelper blackListHelper;

    public static final BigDecimal charges_price_percentage = BigDecimal.valueOf(0.25);

    @Override
    public TicketResponse create(TicketRequest request) {
        blackListHelper.isInBlackListCustomer(request.getIdClient());
        var fly = flyRepository.findById(request.getIdFly())
                .orElseThrow(() -> new IdNotFoundException(Tables.fly.name()));
        var customer = customerRepository.findById(request.getIdClient())
                .orElseThrow(() -> new IdNotFoundException(Tables.customer.name()));

        var ticketToPersist = TicketEntity.builder()
                .id(UUID.randomUUID())
                .fly(fly)
                .customer(customer)
                .price(fly.getPrice().add(fly.getPrice().multiply(charges_price_percentage)))
                .purchaseDate(LocalDate.now())
                .departureDate(BestTravelUtil.getRandomSoon())
                .arrivalDate(BestTravelUtil.getRandomLatter())
                .build();

        var ticketPersisted = this.ticketRepository.save(ticketToPersist);

        this.customerHelper.incrase(customer.getDni(), TicketService.class);

        log.info("Ticket saved with id: {}", ticketPersisted.getId());

        return this.entityToResponse(ticketPersisted);
    }

    @Override
    public TicketResponse read(UUID id) {
        var ticketFromDB = this.ticketRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(Tables.ticket.name()));
        return this.entityToResponse(ticketFromDB);
    }

    @Override
    public TicketResponse update(TicketRequest request, UUID id) {
        var ticketToUpdate = ticketRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(Tables.ticket.name()));
        var fly = flyRepository.findById(request.getIdFly())
                .orElseThrow(() -> new IdNotFoundException(Tables.fly.name()));

        ticketToUpdate.setFly(fly);
        ticketToUpdate.setPrice(fly.getPrice().add(fly.getPrice().multiply(charges_price_percentage)));
        ticketToUpdate.setDepartureDate(BestTravelUtil.getRandomSoon());
        ticketToUpdate.setArrivalDate(BestTravelUtil.getRandomLatter());

        var ticketUpdated = this.ticketRepository.save(ticketToUpdate);

        log.info("Ticket updated with id {}", ticketUpdated.getId());

        return this.entityToResponse(ticketUpdated);
    }

    @Override
    public void delete(UUID id) {
        var ticketToDelete = ticketRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(Tables.ticket.name()));
        ticketRepository.delete(ticketToDelete);
    }

    private TicketResponse entityToResponse(TicketEntity entity) {
        var response = new TicketResponse();
        BeanUtils.copyProperties(entity, response);
        var flyResponse = new FlyResponse();
        BeanUtils.copyProperties(entity.getFly(), flyResponse);
        response.setFly(flyResponse);
        return response;
    }

    @Override
    public BigDecimal findPrice(Long flyId) {
        var fly = this.flyRepository.findById(flyId).orElseThrow(() -> new IdNotFoundException(Tables.fly.name()));
        return fly.getPrice().add(fly.getPrice().multiply(charges_price_percentage));
    }
}
