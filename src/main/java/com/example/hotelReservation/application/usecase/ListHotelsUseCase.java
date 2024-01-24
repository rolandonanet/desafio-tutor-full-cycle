package com.example.hotelReservation.application.usecase;

import com.example.hotelReservation.application.service.HotelService;
import com.example.hotelReservation.entities.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ListHotelsUseCase {
    private final HotelService hotelService;

    public List<Hotel> execute(){
        return hotelService.listHotels();
    }
}
