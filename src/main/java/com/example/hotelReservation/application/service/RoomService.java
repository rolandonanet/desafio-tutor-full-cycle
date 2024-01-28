package com.example.hotelReservation.application.service;

import com.example.hotelReservation.adapter.gateway.repository.HotelGatewayRepository;
import com.example.hotelReservation.entities.Room;
import com.example.hotelReservation.entities.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoomService {
    private final HotelGatewayRepository hotelGatewayRepository;
    private final HotelService hotelService;

    public void createRoom(Long hotelId, Room room) {
        Hotel hotel = hotelService.getHotel(hotelId);

        List<Room> rooms = hotel.getRooms();
        rooms.add(room);

        hotel.setRooms(rooms);
        hotelGatewayRepository.save(hotel);
    }

    public List<Room> listRooms(Long hotelId) {
        Hotel hotel = hotelService.getHotel(hotelId);
        return hotel.getRooms();
    }

    public void updateRoom(Long hotelId, Long roomId, Room room) {
        Hotel hotel = hotelService.getHotel(hotelId);
        Room existingRoom = hotel.getRooms().stream()
                .filter(expectedRoom -> room.getId().equals(roomId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Room not found with Id: " + roomId));

        existingRoom.setNumber(room.getNumber());
        existingRoom.setPrice(room.getPrice());
        existingRoom.setStatus(room.getStatus());

        hotelGatewayRepository.save(hotel);
    }
}
