package com.example.hotelReservation.application.usecase;

import com.example.hotelReservation.application.service.RoomService;
import com.example.hotelReservation.entities.Room;
import com.example.hotelReservation.entities.RoomStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UpdateRoomUseCaseTest {

    @Mock
    private RoomService roomService;

    @InjectMocks
    private UpdateRoomUseCase updateRoomUseCase;

    @Test
    void testUpdateRoom() {
        Long hotelId = 1L;
        Long roomId = 1L;
        Room updatedRoom = buildSampleRoom();

        updateRoomUseCase.execute(hotelId, roomId, updatedRoom);

        verify(roomService).updateRoom(eq(hotelId), eq(roomId), eq(updatedRoom));
    }

    private Room buildSampleRoom() {
        return Room.builder()
                .id(1L)
                .number(101)
                .price(BigDecimal.valueOf(100))
                .status(RoomStatus.AVAILABLE)
                .build();
    }
}
