package com.example.hotelReservation.application.service;

import com.example.hotelReservation.adapter.gateway.repository.HotelGatewayRepository;
import com.example.hotelReservation.entities.Address;
import com.example.hotelReservation.entities.Hotel;
import com.example.hotelReservation.entities.Room;
import com.example.hotelReservation.entities.RoomStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    @Mock
    private HotelGatewayRepository hotelGatewayRepository;

    @Mock
    private HotelService hotelService;

    @InjectMocks
    private RoomService roomService;

    @Test
    void testCreateRoom() {
        Long hotelId = 1L;
        Hotel hotel = buildSampleHotel();
        Room room = buildSampleRoom();

        when(hotelService.getHotel(hotelId)).thenReturn(hotel);

        roomService.createRoom(hotelId, room);

        verify(hotelService).getHotel(hotelId);
        verify(hotelGatewayRepository).save(hotel);
    }


    @Test
    void testListRooms() {
        Long hotelId = 1L;
        Hotel hotel = buildSampleHotel();

        when(hotelService.getHotel(hotelId)).thenReturn(hotel);

        List<Room> actualRooms = roomService.listRooms(hotelId);

        verify(hotelService).getHotel(hotelId);

        assertEquals(hotel.getRooms(), actualRooms);
    }

    @Test
    void testUpdateRoom() {
        Long hotelId = 1L;
        Long roomId = 1L;
        Hotel hotel = buildSampleHotel();
        Room updatedRoom = buildSampleRoom();

        when(hotelService.getHotel(hotelId)).thenReturn(hotel);

        roomService.updateRoom(hotelId, roomId, updatedRoom);

        verify(hotelService).getHotel(hotelId);
        verify(hotelGatewayRepository).save(hotel);
    }

    @Test
    void testUpdateRoomWithNonexistentId() {
        Long hotelId = 1L;
        Long roomId = 999L;
        Room updatedRoom = buildSampleRoom();

        when(hotelService.getHotel(hotelId)).thenReturn(buildSampleHotel());

        assertThrows(IllegalArgumentException.class, () -> roomService.updateRoom(hotelId, roomId, updatedRoom));

        verify(hotelService).getHotel(hotelId);
        verify(hotelGatewayRepository, never()).save(any(Hotel.class));
    }

    private Hotel buildSampleHotel() {
        return Hotel.builder()
                .id(1L)
                .name("Sample Hotel")
                .address(Address.builder().street("Sample Street").zipcode("12345").country("Sample Country").build())
                .rooms(new ArrayList<>(Collections.singletonList(buildSampleRoom())))
                .build();
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
