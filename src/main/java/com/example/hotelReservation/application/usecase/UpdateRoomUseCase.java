package com.example.hotelReservation.application.usecase;

import com.example.hotelReservation.application.service.RoomService;
import com.example.hotelReservation.entities.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UpdateRoomUseCase {

    private final RoomService roomService;
    public void execute(Long hotelId, Long roomId, Room room) {
        roomService.updateRoom(hotelId, roomId, room);
    }
}
