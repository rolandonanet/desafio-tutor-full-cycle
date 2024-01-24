package com.example.hotelReservation.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelResponseDTO {
    private Long id;
    private String name;
    private AddressResponseDTO address;
    private List<RoomResponseDTO> rooms;
    private Integer roomsAvailable;
    private Integer roomsBooked;
}
