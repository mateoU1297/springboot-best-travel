package com.udemy.best_travel.api.models.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TourRequest {

    private String customerId;
    private Set<TourFlyRequest> flights;
    private Set<TourHotelRequest> hotels;
}
