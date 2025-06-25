package com.example.propertyview.controller;

import com.example.propertyview.dto.HotelCreateDTO;
import com.example.propertyview.dto.HotelDetailsDTO;
import com.example.propertyview.dto.HotelShortDTO;
import com.example.propertyview.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/property-view")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @GetMapping("/hotels")
    public List<HotelShortDTO> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping("/hotels/{id}")
    public HotelDetailsDTO getHotelById(@PathVariable Long id) {
        return hotelService.getHotelById(id);
    }

    @GetMapping("/search")
    public List<HotelShortDTO> searchHotels(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) Set<String> amenities) {

        return hotelService.searchHotels(name, brand, city, country, amenities);
    }

    @PostMapping("/hotels")
    @ResponseStatus(HttpStatus.CREATED)
    public HotelShortDTO createHotel(@RequestBody HotelCreateDTO dto) {
        return hotelService.createHotel(dto);
    }

    @PostMapping("/hotels/{id}/amenities")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addAmenities(@PathVariable Long id, @RequestBody List<String> amenities) {
        hotelService.addAmenities(id, amenities);
    }

    @GetMapping("/histogram/{param}")
    public Map<String, Long> getHistogram(@PathVariable String param) {
        return hotelService.getHistogram(param);
    }
}