package com.champsoft.vrms.vehicle.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private Integer modelYear;

    @Column(nullable = false, unique = true)
    private String plateNumber;

    @Column(nullable = false)
    private Boolean available;

    @Column(nullable = false)
    private String conditionStatus;

    @Column(nullable = false)
    private Double dailyRate;
}

