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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateRoomUseCaseTest {

    @Mock
    private RoomService roomService;

    @InjectMocks
    private CreateRoomUseCase createRoomUseCase;

    @Test
    void testCreateRoom() {
        Long hotelId = 1L;
        Room room = buildSampleRoom();

        createRoomUseCase.execute(hotelId, room);

        verify(roomService).createRoom(eq(hotelId), eq(room));
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
