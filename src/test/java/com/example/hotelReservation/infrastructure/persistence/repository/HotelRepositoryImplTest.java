package com.example.hotelReservation.infrastructure.persistence.repository;

import com.example.hotelReservation.adapter.gateway.repository.HotelGatewayRepository;
import com.example.hotelReservation.entities.Hotel;
import com.example.hotelReservation.infrastructure.persistence.entity.HotelEntity;
import com.example.hotelReservation.infrastructure.persistence.jpa.HotelJpaRepository;
import com.example.hotelReservation.infrastructure.persistence.mapper.HotelMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HotelRepositoryImplTest {

    @Mock
    private HotelJpaRepository hotelJpaRepository;

    @Mock
    private HotelMapper hotelMapper;

    @InjectMocks
    private HotelRepositoryImpl hotelRepository;

    @Test
    void testFindByName() {
        String hotelName = "Sample Hotel";
        HotelEntity hotelEntity = new HotelEntity();
        Hotel hotel = new Hotel();
        when(hotelJpaRepository.findFirstByName(hotelName)).thenReturn(hotelEntity);

        Optional<Hotel> result = hotelRepository.findByName(hotelName);

        assertTrue(result.isPresent());
        assertEquals(hotel, result.get());
        verify(hotelJpaRepository).findFirstByName(hotelName);
    }

    @Test
    void testExists() {

        Long hotelId = 1L;
        when(hotelJpaRepository.existsById(hotelId)).thenReturn(true);

        Boolean result = hotelRepository.exists(hotelId);

        assertTrue(result);
        verify(hotelJpaRepository).existsById(hotelId);
    }

    @Test
    void testSave() {
        Hotel hotel = new Hotel();
        HotelEntity hotelEntity = new HotelEntity();

        hotelRepository.save(hotel);

        verify(hotelJpaRepository).save(hotelEntity);
    }

    @Test
    void testList() {
        List<HotelEntity> hotelEntities = Arrays.asList(new HotelEntity(), new HotelEntity());
        List<Hotel> expectedHotels = Arrays.asList(new Hotel(), new Hotel());
        when(hotelJpaRepository.findAll()).thenReturn(hotelEntities);

        List<Hotel> result = hotelRepository.list();

        assertEquals(expectedHotels, result);
        verify(hotelJpaRepository).findAll();
    }

    @Test
    void testFindById() {
        Long hotelId = 1L;
        HotelEntity hotelEntity = new HotelEntity();
        Hotel expectedHotel = new Hotel();
        when(hotelJpaRepository.findById(hotelId)).thenReturn(Optional.of(hotelEntity));

        Hotel result = hotelRepository.findById(hotelId);

        assertEquals(expectedHotel, result);
        verify(hotelJpaRepository).findById(hotelId);
    }
}
