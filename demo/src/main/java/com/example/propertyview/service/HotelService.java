package com.example.propertyview.service;

import com.example.propertyview.dto.HotelCreateDTO;
import com.example.propertyview.dto.HotelDetailsDTO;
import com.example.propertyview.dto.HotelShortDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface HotelService {
    List<HotelShortDTO> getAllHotels();
    HotelDetailsDTO getHotelById(Long id);
    List<HotelShortDTO> searchHotels(String name, String brand, String city, String country, Set<String> amenities);
    HotelShortDTO createHotel(HotelCreateDTO dto);
    void addAmenities(Long hotelId, List<String> amenities);
    Map<String, Long> getHistogram(String param);
}