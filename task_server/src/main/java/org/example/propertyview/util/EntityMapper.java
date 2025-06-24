package org.example.propertyview.util;

import org.example.propertyview.dto.*;
import org.example.propertyview.model.Address;
import org.example.propertyview.model.Contacts;
import org.example.propertyview.model.Hotel;
import org.example.propertyview.model.Time;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EntityMapper {


    public HotelShortDTO toHotelShortDTO(Hotel hotel) {
        return new HotelShortDTO(
                hotel.getId(),
                hotel.getName(),
                hotel.getDescription(),
                formatAddress(hotel.getAddress()),
                hotel.getContacts().getPhone()
        );
    }


    public HotelDetailsDTO toHotelDetailsDTO(Hotel hotel) {
        return new HotelDetailsDTO(
                hotel.getId(),
                hotel.getName(),
                hotel.getDescription(),
                hotel.getBrand(),
                toAddressDTO(hotel.getAddress()),
                toContactsDTO(hotel.getContacts()),
                toTimeDTO(hotel.getArrivalTime()),
                hotel.getAmenities()
        );
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


    public AddressDTO toAddressDTO(Address address) {
        return new AddressDTO(
                address.getHouseNumber(),
                address.getStreet(),
                address.getCity(),
                address.getCountry(),
                address.getPostCode()
        );
    }


    public Address toAddressEntity(AddressDTO dto) {
        Address address = new Address();
        address.setHouseNumber(dto.getHouseNumber());
        address.setStreet(dto.getStreet());
        address.setCity(dto.getCity());
        address.setCountry(dto.getCountry());
        address.setPostCode(dto.getPostCode());
        return address;
    }

    public ContactsDTO toContactsDTO(Contacts contacts) {
        return new ContactsDTO(
                contacts.getPhone(),
                contacts.getEmail()
        );
    }

    public Contacts toContactsEntity(ContactsDTO dto) {
        Contacts contacts = new Contacts();
        contacts.setPhone(dto.getPhone());
        contacts.setEmail(dto.getEmail());
        return contacts;
    }

    public TimeDTO toTimeDTO(Time time) {
        return new TimeDTO(
                time.getCheckIn(),
                time.getCheckOut()
        );
    }


    public Time toTimeEntity(TimeDTO dto) {
        Time time = new Time();
        time.setCheckIn(dto.getCheckIn());
        time.setCheckOut(dto.getCheckOut());
        return time;
    }

    private String formatAddress(Address address) {
        return String.format("%d %s, %s, %s, %s",
                address.getHouseNumber(),
                address.getStreet(),
                address.getCity(),
                address.getPostCode(),
                address.getCountry());
    }
}
