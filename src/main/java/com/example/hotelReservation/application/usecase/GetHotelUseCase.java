package com.example.hotelReservation.application.usecase;

import com.example.hotelReservation.application.service.HotelService;
import com.example.hotelReservation.entities.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetHotelUseCase {

    private final HotelService hotelService;
    public Hotel execute(Long hotelId) {
        return hotelService.getHotel(hotelId);
    }
}
