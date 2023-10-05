package com.udemy.best_travel.api.controllers;

import com.udemy.best_travel.api.models.request.TourRequest;
import com.udemy.best_travel.api.models.response.TourResponse;
import com.udemy.best_travel.infraestructure.abstract_services.ITourService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "tour")
@AllArgsConstructor
public class TourController {

    private final ITourService tourService;

    @PostMapping
    public ResponseEntity<TourResponse> post(@RequestBody TourRequest request) {
        return ResponseEntity.ok(this.tourService.create(request));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<TourResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(this.tourService.read(id));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<TourResponse> delete(@PathVariable Long id) {
        this.tourService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
