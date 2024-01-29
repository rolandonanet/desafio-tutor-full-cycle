package com.example.hotelReservation.application.service;

import com.example.hotelReservation.adapter.gateway.messaging.RabbitMQProducerGateway;
import com.example.hotelReservation.adapter.gateway.repository.HotelGatewayRepository;
import com.example.hotelReservation.entities.Book;
import com.example.hotelReservation.entities.Hotel;
import com.example.hotelReservation.entities.RoomStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class HotelService {

    private final HotelGatewayRepository hotelGatewayRepository;
    private final RabbitMQProducerGateway rabbitMQProducerGateway;

    public void createHotel(Hotel hotel) {
        if (hotelGatewayRepository.findByName(hotel.getName()).isPresent()) {
            throw new RuntimeException("A hotel with the same name already exists.");
        }
        hotelGatewayRepository.save(hotel);
        rabbitMQProducerGateway.sendMessage(hotel);
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
        LocalDate today = LocalDate.now();

        hotel.getRooms().forEach(room -> {
            boolean isBookedToday = room.getBookings().stream()
                    .anyMatch(book -> isBookingOnDate(book, today));

            room.setStatus(isBookedToday ? RoomStatus.UNAVAILABLE : RoomStatus.AVAILABLE);
        });

        long roomsAvailable = hotel.getRooms().stream()
                .filter(room -> room.getStatus() == RoomStatus.AVAILABLE)
                .count();

        hotel.setRoomsAvailable((int) roomsAvailable);
        hotel.setRoomsBooked(hotel.getRooms().size() - (int) roomsAvailable);
    }

    private boolean isBookingOnDate(Book book, LocalDate date) {
        return !date.isBefore(book.getStartDate()) && !date.isAfter(book.getEndDate());
    }

}
