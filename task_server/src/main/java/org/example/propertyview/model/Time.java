package org.example.propertyview.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Time {
    @Column(name = "check_in", length = 10, nullable = false)
    private String checkIn;

    @Column(name = "check_out", length = 10)
    private String checkOut;
}