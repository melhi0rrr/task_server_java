package com.example.propertyview.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hotel")
@Data
@NoArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private String brand;

    @Embedded
    private Address address;

    @Embedded
    private Contacts contacts;

    @Embedded
    private Time arrivalTime;

    @ElementCollection
    @CollectionTable(
            name = "hotel_amenities",
            joinColumns = @JoinColumn(name = "hotel_id")
    )
    @Column(name = "amenity")
    private Set<String> amenities = new HashSet<>();
}