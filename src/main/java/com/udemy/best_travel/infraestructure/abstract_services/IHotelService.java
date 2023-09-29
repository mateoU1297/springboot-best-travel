package com.udemy.best_travel.infraestructure.abstract_services;

import com.udemy.best_travel.api.models.response.HotelResponse;

import java.util.Set;

public interface IHotelService extends CatalogService<HotelResponse> {

    Set<HotelResponse> readByRating(Integer rating);
}
