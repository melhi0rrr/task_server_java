package com.example.propertyview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelShortDTO {
    private Long id;
    private String name;
    private String description;
    private String address;
    private String phone;
}