package com.udemy.best_travel.api.models.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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

    @Positive
    @NotNull
    private String customerId;

    @NotNull
    @Size(min = 1)
    private Set<TourFlyRequest> flights;

    @NotNull
    @Size(min = 1)
    private Set<TourHotelRequest> hotels;
}
