package com.example.hotelReservation.adapter.dto;

import com.example.hotelReservation.entities.RoomStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomRequestDTO {
    private Integer number;
    private BigDecimal price;
    private RoomStatus status;
}
