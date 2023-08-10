package com.udemy.best_travel.infraestructure.abstract_services;

import com.udemy.best_travel.api.models.request.TicketRequest;
import com.udemy.best_travel.api.models.response.TicketResponse;

import java.math.BigDecimal;
import java.util.UUID;

public interface ITicketService extends CrudService<TicketRequest, TicketResponse, UUID> {

    BigDecimal findPrice(Long flyId);
}
