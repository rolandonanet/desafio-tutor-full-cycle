package com.example.hotelReservation.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
    private Long id;
    private String name;
    private Address address;
    private List<Room> rooms;
    private Integer roomsAvailable;
    private Integer roomsBooked;
}
