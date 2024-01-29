package com.example.hotelReservation.application.service;

import com.example.hotelReservation.adapter.gateway.repository.HotelGatewayRepository;
import com.example.hotelReservation.entities.Book;
import com.example.hotelReservation.entities.Room;
import com.example.hotelReservation.entities.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
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

    public void createRoomBooking(Long hotelId, Long roomId, Book book) {
        Hotel hotel = hotelService.getHotel(hotelId);
        Room existingRoom = filterRoomById(hotel, roomId);

        checkBookingConflict(existingRoom, book);

        existingRoom.getBookings().add(book);
        updateRoom(hotelId, roomId, existingRoom);
    }

    public List<Room> listRooms(Long hotelId) {
        Hotel hotel = hotelService.getHotel(hotelId);
        return hotel.getRooms();
    }

    public void updateRoom(Long hotelId, Long roomId, Room room) {
        Hotel hotel = hotelService.getHotel(hotelId);
        Room existingRoom = filterRoomById(hotel, roomId);
        existingRoom.setNumber(room.getNumber());
        existingRoom.setPrice(room.getPrice());
        existingRoom.setStatus(room.getStatus());
        existingRoom.setBookings(room.getBookings());

        hotelGatewayRepository.save(hotel);
    }

    private Room filterRoomById(Hotel hotel, Long roomId) {
        return hotel.getRooms().stream()
                .filter(expectedRoom -> expectedRoom.getId().equals(roomId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Room not found with Id: " + roomId));
    }

    private void checkBookingConflict(Room room, Book newBook) {
        if (room.getBookings().stream().anyMatch(existingBook -> hasDateConflict(existingBook, newBook))) {
            throw new RuntimeException("A nova reserva conflita com uma reserva existente.");
        }
    }

    private boolean hasDateConflict(Book existingBook, Book newBook) {
        return newBook.getStartDate().isBefore(existingBook.getEndDate()) &&
                newBook.getEndDate().isAfter(existingBook.getStartDate());
    }
}
