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
public class Contacts {
    @Column(length = 50)
    private String phone;

    @Column(length = 100)
    private String email;
}
