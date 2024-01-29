package com.example.hotelReservation.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    private Long id;
    private Integer number;
    private BigDecimal price;
    private RoomStatus status;
    private List<Book> bookings;
}
