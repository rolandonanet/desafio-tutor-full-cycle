package com.example.hotelReservation.application.usecase;

import com.example.hotelReservation.application.service.HotelService;
import com.example.hotelReservation.entities.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateHotelUseCase {

    private final HotelService hotelService;
    public void execute(Hotel hotel){
       hotelService.createHotel(hotel);
    }
}
