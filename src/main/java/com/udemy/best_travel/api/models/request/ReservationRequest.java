package com.udemy.best_travel.api.models.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ReservationRequest {

    @Size(min = 18, max = 20)
    @NotBlank
    private String idClient;

    @Positive
    @NotNull
    private Long idHotel;

    @Min(value = 1)
    @Max(value = 30)
    @NotNull
    private Integer totalDays;

    @Email
    private String email;
}
