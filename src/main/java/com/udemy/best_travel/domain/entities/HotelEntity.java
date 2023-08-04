package com.udemy.best_travel.domain.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "hotel")
public class HotelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String address;
    private Integer rating;
    private BigDecimal price;
}
