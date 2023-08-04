package com.udemy.best_travel.domain.entities;

import com.udemy.best_travel.util.AeroLine;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "fly")
public class FlyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double originLat;
    private Double originLng;
    private Double destinyLat;
    private Double destinyLng;
    @Column(length = 20)
    private String originName;
    @Column(length = 20)
    private String destinyName;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private AeroLine aeroLine;
}
