package com.example.propertyview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmenityHistogramDTO {
    private String amenityName;
    private Long count;
}