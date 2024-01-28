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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class HotelServiceTest {

    @Mock
    private HotelGatewayRepository hotelGatewayRepository;

    @InjectMocks
    private HotelService hotelService;

    @Test
    void testCreateHotel() {
        Hotel hotel = buildSampleHotel();

        when(hotelGatewayRepository.findByName(hotel.getName())).thenReturn(Optional.empty());

        hotelService.createHotel(hotel);

        verify(hotelGatewayRepository).findByName(hotel.getName());
        verify(hotelGatewayRepository).save(hotel);
    }

    @Test
    void testCreateHotelWithExistingName() {
        Hotel hotel = buildSampleHotel();

        when(hotelGatewayRepository.findByName(hotel.getName())).thenReturn(Optional.of(hotel));

        assertThrows(RuntimeException.class, () -> hotelService.createHotel(hotel));

        verify(hotelGatewayRepository).findByName(hotel.getName());
        verify(hotelGatewayRepository, never()).save(any(Hotel.class));
    }

    @Test
    void testListHotels() {
        List<Hotel> expectedHotels = Collections.singletonList(buildSampleHotel());


        when(hotelGatewayRepository.list()).thenReturn(expectedHotels);

        List<Hotel> actualHotels = hotelService.listHotels();

        verify(hotelGatewayRepository).list();

        assertEquals(expectedHotels, actualHotels);
    }

    @Test
    void testUpdateHotel() {
        Long hotelId = 1L;
        Hotel updatedHotel = buildSampleHotel();

        when(hotelGatewayRepository.findById(hotelId)).thenReturn(updatedHotel);

        hotelService.updateHotel(hotelId, updatedHotel);

        verify(hotelGatewayRepository).findById(hotelId);
        verify(hotelGatewayRepository).save(updatedHotel);
    }

    @Test
    void testUpdateHotelWithNonexistentId() {
        Long hotelId = 1L;
        Hotel updatedHotel = buildSampleHotel();

        when(hotelGatewayRepository.findById(hotelId)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> hotelService.updateHotel(hotelId, updatedHotel));

        verify(hotelGatewayRepository).findById(hotelId);
        verify(hotelGatewayRepository, never()).save(any(Hotel.class));
    }

    @Test
    void testGetHotel() {
        Long hotelId = 1L;
        Hotel expectedHotel = buildSampleHotel();

        when(hotelGatewayRepository.findById(hotelId)).thenReturn(expectedHotel);

        Hotel actualHotel = hotelService.getHotel(hotelId);

        verify(hotelGatewayRepository).findById(hotelId);

        assertEquals(expectedHotel, actualHotel);
    }

    @Test
    void testGetHotelWithNonexistentId() {
        Long hotelId = 1L;

        when(hotelGatewayRepository.findById(hotelId)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> hotelService.getHotel(hotelId));

        verify(hotelGatewayRepository).findById(hotelId);
    }

    private Hotel buildSampleHotel() {
        return Hotel.builder()
                .id(1L)
                .name("Sample Hotel")
                .address(Address.builder().street("Sample Street").zipcode("12345").country("Sample Country").build())
                .rooms(List.of(buildSampleRoom()))
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
