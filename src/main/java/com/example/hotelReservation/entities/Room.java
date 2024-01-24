package com.example.hotelReservation.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    private Long id;
    private Integer number;
    private BigDecimal price;
    private RoomStatus status;
}
