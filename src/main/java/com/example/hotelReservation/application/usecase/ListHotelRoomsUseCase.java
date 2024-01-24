package com.example.hotelReservation.application.usecase;

import com.example.hotelReservation.application.service.RoomService;
import com.example.hotelReservation.entities.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ListHotelRoomsUseCase {
    private final RoomService roomService;
    public List<Room> execute(Long hotelId) {
        return roomService.listRooms(hotelId);
    }
}
