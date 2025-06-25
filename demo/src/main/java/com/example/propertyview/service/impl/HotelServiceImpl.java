package com.example.propertyview.service.impl;

import com.example.propertyview.dto.*;
import com.example.propertyview.exception.HotelNotFoundException;
import com.example.propertyview.model.Hotel;
import com.example.propertyview.repository.HotelRepository;
import com.example.propertyview.service.HotelService;
import com.example.propertyview.util.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final EntityMapper entityMapper;

    @Override
    public List<HotelShortDTO> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(entityMapper::toHotelShortDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HotelDetailsDTO getHotelById(Long id) {
        return hotelRepository.findById(id)
                .map(entityMapper::toHotelDetailsDTO)
                .orElseThrow(() -> new HotelNotFoundException(id));
    }

    @Override
    public List<HotelShortDTO> searchHotels(String name, String brand, String city, String country, Set<String> amenities) {
        List<Hotel> hotels = hotelRepository.searchHotels(name, brand, city, country);

        if (amenities != null && !amenities.isEmpty()) {
            hotels = filterHotelsByAmenities(hotels, amenities);
        }

        return hotels.stream()
                .map(entityMapper::toHotelShortDTO)
                .collect(Collectors.toList());
    }

    private List<Hotel> filterHotelsByAmenities(List<Hotel> hotels, Set<String> requiredAmenities) {
        return hotels.stream()
                .filter(hotel -> hotel.getAmenities().containsAll(requiredAmenities))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public HotelShortDTO createHotel(HotelCreateDTO dto) {
        Hotel hotel = entityMapper.toHotelEntity(dto);
        Hotel savedHotel = hotelRepository.save(hotel);
        return entityMapper.toHotelShortDTO(savedHotel);
    }

    @Override
    @Transactional
    public void addAmenities(Long hotelId, List<String> amenities) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new HotelNotFoundException(hotelId));

        Set<String> existingAmenities = hotel.getAmenities();
        existingAmenities.addAll(amenities);

        hotelRepository.save(hotel);
    }

    @Override
    public Map<String, Long> getHistogram(String param) {
        return hotelRepository.getHistogramData(param);
    }
}