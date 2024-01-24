package com.example.hotelReservation.application.service;

import com.example.hotelReservation.adapter.gateway.repository.HotelGatewayRepository;
import com.example.hotelReservation.entities.Hotel;
import com.example.hotelReservation.entities.Room;
import com.example.hotelReservation.entities.RoomStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HotelService {

    private final HotelGatewayRepository hotelGatewayRepository;

    public void createHotel(Hotel hotel) {
        if (hotelGatewayRepository.findByName(hotel.getName()).isPresent()) {
            throw new RuntimeException("A hotel with the same name already exists.");
        }
        hotelGatewayRepository.save(hotel);
    }

    public List<Hotel> listHotels() {
        List<Hotel> hotels = hotelGatewayRepository.list();
        for (Hotel hotel : hotels) {
            calculateRoomStats(hotel);
        }
        return hotels;
    }

    public void updateHotel(Long hotelId, Hotel hotel) {
        getHotel(hotelId);
        hotel.setId(hotelId);
        hotelGatewayRepository.save(hotel);
    }

    public Hotel getHotel(Long hotelId){
        Hotel hotel = hotelGatewayRepository.findById(hotelId);
        if (hotel == null) {
            throw new IllegalArgumentException("Hotel not found with ID: " + hotelId);
        }
        calculateRoomStats(hotel);
        return hotel;
    }

    private void calculateRoomStats(Hotel hotel) {
        Map<RoomStatus, Long> roomStats = hotel.getRooms().stream()
                .collect(Collectors.groupingBy(Room::getStatus, Collectors.counting()));

        hotel.setRoomsAvailable(roomStats.getOrDefault(RoomStatus.AVAILABLE, 0L).intValue());
        hotel.setRoomsBooked(roomStats.getOrDefault(RoomStatus.UNAVAILABLE, 0L).intValue());
    }

}
