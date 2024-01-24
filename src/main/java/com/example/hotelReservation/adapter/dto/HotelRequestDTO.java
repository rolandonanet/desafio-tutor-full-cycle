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
public class HotelRequestDTO {
    private String name;
    private AddressRequestDTO address;
    private List<RoomRequestDTO> rooms;
}

