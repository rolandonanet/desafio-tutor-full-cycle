package com.example.hotelReservation.application.usecase;

import com.example.hotelReservation.application.service.RoomService;
import com.example.hotelReservation.entities.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateBookingUseCase {

    private final RoomService roomService;
    public void execute(Long hotelId, Long roomId, Book book){roomService.createRoomBooking(hotelId,roomId,book);
    }
}
