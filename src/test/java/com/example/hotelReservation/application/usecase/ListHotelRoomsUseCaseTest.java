package com.example.hotelReservation.application.usecase;

import com.example.hotelReservation.application.service.RoomService;
import com.example.hotelReservation.entities.Room;
import com.example.hotelReservation.entities.RoomStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ListHotelRoomsUseCaseTest {

    @Mock
    private RoomService roomService;

    @InjectMocks
    private ListHotelRoomsUseCase listHotelRoomsUseCase;

    @Test
    void testListHotelRooms() {
        Long hotelId = 1L;
        List<Room> expectedRooms = Collections.singletonList(buildSampleRoom());

        when(roomService.listRooms(eq(hotelId))).thenReturn(expectedRooms);

        List<Room> actualRooms = listHotelRoomsUseCase.execute(hotelId);

        verify(roomService).listRooms(eq(hotelId));

        assertEquals(expectedRooms, actualRooms);
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
