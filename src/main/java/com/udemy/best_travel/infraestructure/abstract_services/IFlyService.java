package com.udemy.best_travel.infraestructure.abstract_services;

import com.udemy.best_travel.api.models.response.FlyResponse;

import java.util.Set;

public interface IFlyService extends CatalogService<FlyResponse> {

    Set<FlyResponse> readByOriginDestiny(String origen, String destiny);
}
