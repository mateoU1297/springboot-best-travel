package com.udemy.best_travel.api.controllers;

import com.udemy.best_travel.api.models.request.TourRequest;
import com.udemy.best_travel.api.models.response.TourResponse;
import com.udemy.best_travel.infraestructure.abstract_services.ITourService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "tour")
@AllArgsConstructor
@Tag(name = "Tour")
public class TourController {

    private final ITourService tourService;

    @Operation(summary = "save in system a tour based in list of hotels and flights")
    @PostMapping
    public ResponseEntity<TourResponse> post(@Valid @RequestBody TourRequest request) {
        return ResponseEntity.ok(this.tourService.create(request));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<TourResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(this.tourService.read(id));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.tourService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "{tourId}/remove-ticket/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long tourId, @PathVariable UUID ticketId) {
        this.tourService.removeTicket(ticketId, tourId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "{tourId}/add-ticket/{flyId}")
    public ResponseEntity<Map<String, UUID>> addTicket(@PathVariable Long tourId, @PathVariable Long flyId) {
        var response = Collections.singletonMap("ticketId", this.tourService.addTicket(tourId, flyId));
        return ResponseEntity.ok(response);
    }

    @PatchMapping(path = "{tourId}/remove-reservation/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long tourId, @PathVariable UUID reservationId) {
        this.tourService.removeReservation(reservationId, tourId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "{tourId}/add-reservation/{hotelId}")
    public ResponseEntity<Map<String, UUID>> addReservation(
            @PathVariable Long tourId,
            @PathVariable Long hotelId,
            @RequestParam Integer totalDays) {
        var response = Collections.singletonMap("reservationId", this.tourService.addReservation(tourId, hotelId, totalDays));
        return ResponseEntity.ok(response);
    }
}
