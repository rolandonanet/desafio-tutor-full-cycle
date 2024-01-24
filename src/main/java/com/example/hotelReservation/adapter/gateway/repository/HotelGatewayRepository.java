package com.example.hotelReservation.adapter.gateway.repository;

import com.example.hotelReservation.entities.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelGatewayRepository {

    Boolean exists(Long hotelId);
    void save(Hotel hotel);
    List<Hotel> list();
    Hotel findById(Long hotelId);
    Optional<Hotel> findByName(String name);
}
