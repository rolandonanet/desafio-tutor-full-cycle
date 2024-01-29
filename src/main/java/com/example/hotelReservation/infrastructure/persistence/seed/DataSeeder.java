package com.example.hotelReservation.infrastructure.persistence.seed;

import com.example.hotelReservation.entities.*;
import com.example.hotelReservation.infrastructure.persistence.repository.HotelRepositoryImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
public class DataSeeder {
    private final HotelRepositoryImpl hotelRepositoryImpl;

    @Transactional
    public void seedData() {
        Hotel hotel1 = createHotel("Hotel A");
        Hotel hotel2 = createHotel("Hotel B");

        hotelRepositoryImpl.save(hotel1);
        hotelRepositoryImpl.save(hotel2);
    }

    private Hotel createHotel(String name) {
        Address address = Address.builder()
                .street("123 Main Street")
                .zipcode("12345")
                .country("Country")
                .build();

        List<Room> rooms = createRooms(10);

        return Hotel.builder()
                .name(name)
                .address(address)
                .rooms(rooms)
                .build();
    }

    private List<Room> createRooms(int numberOfRooms) {
        List<Room> rooms = new ArrayList<>();
        for (int i = 1; i <= numberOfRooms; i++) {
            Room room = Room.builder()
                    .number(i)
                    .price(BigDecimal.valueOf(100))
                    .status(RoomStatus.AVAILABLE)
                    .bookings(createBookings(LocalDate.now(), LocalDate.now().plusDays(3)))
                    .build();
            rooms.add(room);
        }
        return rooms;
    }

    private List<Book> createBookings(LocalDate startDate, LocalDate endDate) {
        Book booking = Book.builder()
                .startDate(startDate)
                .endDate(endDate)
                .build();
        return Collections.singletonList(booking);
    }
}
