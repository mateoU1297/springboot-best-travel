package com.udemy.best_travel.api.models.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TourHotelRequest {

    @NotNull
    @Positive
    private Long id;

    @NotNull
    @Positive
    @Min(value = 1)
    @Max(value = 30)
    private Integer totalDays;
}
