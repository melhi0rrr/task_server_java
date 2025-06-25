package com.example.propertyview.util;

import com.example.propertyview.dto.*;
import com.example.propertyview.model.Address;
import com.example.propertyview.model.Contacts;
import com.example.propertyview.model.Hotel;
import com.example.propertyview.model.Time;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {

    public HotelShortDTO toHotelShortDTO(Hotel hotel) {
        HotelShortDTO dto = new HotelShortDTO();
        dto.setId(hotel.getId());
        dto.setName(hotel.getName());
        dto.setDescription(hotel.getDescription());
        dto.setAddress(formatAddress(hotel.getAddress()));
        dto.setPhone(hotel.getContacts() != null ? hotel.getContacts().getPhone() : null);
        return dto;
    }

    public HotelDetailsDTO toHotelDetailsDTO(Hotel hotel) {
        HotelDetailsDTO dto = new HotelDetailsDTO();
        dto.setId(hotel.getId());
        dto.setName(hotel.getName());
        dto.setDescription(hotel.getDescription());
        dto.setBrand(hotel.getBrand());
        dto.setAddress(toAddressDTO(hotel.getAddress()));
        dto.setContacts(toContactsDTO(hotel.getContacts()));
        dto.setArrivalTime(toTimeDTO(hotel.getArrivalTime()));
        dto.setAmenities(hotel.getAmenities());
        return dto;
    }

    public Hotel toHotelEntity(HotelCreateDTO dto) {
        Hotel hotel = new Hotel();
        hotel.setName(dto.getName());
        hotel.setDescription(dto.getDescription());
        hotel.setBrand(dto.getBrand());
        hotel.setAddress(toAddressEntity(dto.getAddress()));
        hotel.setContacts(toContactsEntity(dto.getContacts()));
        hotel.setArrivalTime(toTimeEntity(dto.getArrivalTime()));
        return hotel;
    }

    private AddressDTO toAddressDTO(Address address) {
        if (address == null) return null;
        AddressDTO dto = new AddressDTO();
        dto.setHouseNumber(address.getHouseNumber());
        dto.setStreet(address.getStreet());
        dto.setCity(address.getCity());
        dto.setCountry(address.getCountry());
        dto.setPostCode(address.getPostCode());
        return dto;
    }

    private ContactsDTO toContactsDTO(Contacts contacts) {
        if (contacts == null) return null;
        ContactsDTO dto = new ContactsDTO();
        dto.setPhone(contacts.getPhone());
        dto.setEmail(contacts.getEmail());
        return dto;
    }

    private TimeDTO toTimeDTO(Time time) {
        if (time == null) return null;
        TimeDTO dto = new TimeDTO();
        dto.setCheckIn(time.getCheckIn());
        dto.setCheckOut(time.getCheckOut());
        return dto;
    }

    private Address toAddressEntity(AddressDTO dto) {
        if (dto == null) return null;
        Address address = new Address();
        address.setHouseNumber(dto.getHouseNumber());
        address.setStreet(dto.getStreet());
        address.setCity(dto.getCity());
        address.setCountry(dto.getCountry());
        address.setPostCode(dto.getPostCode());
        return address;
    }

    private Contacts toContactsEntity(ContactsDTO dto) {
        if (dto == null) return null;
        Contacts contacts = new Contacts();
        contacts.setPhone(dto.getPhone());
        contacts.setEmail(dto.getEmail());
        return contacts;
    }

    private Time toTimeEntity(TimeDTO dto) {
        if (dto == null) return null;
        Time time = new Time();
        time.setCheckIn(dto.getCheckIn());
        time.setCheckOut(dto.getCheckOut());
        return time;
    }

    private String formatAddress(Address address) {
        if (address == null) return null;
        return address.getHouseNumber() + " " + address.getStreet() + ", " +
                address.getCity() + ", " + address.getPostCode() + ", " + address.getCountry();
    }
}