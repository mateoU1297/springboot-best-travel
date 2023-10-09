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
public class TicketRequest {

    @Size(min = 18, max = 20)
    @NotBlank
    private String idClient;

    @NotNull
    @Positive
    private Long idFly;

    @Email
    private String email;
}
