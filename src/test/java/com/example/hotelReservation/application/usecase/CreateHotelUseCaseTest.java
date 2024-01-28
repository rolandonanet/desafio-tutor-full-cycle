package com.example.hotelReservation.application.usecase;

import com.example.hotelReservation.application.service.HotelService;
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

import java.math.BigDecimal;
import java.util.Collections;


@ExtendWith(MockitoExtension.class)
public class CreateHotelUseCaseTest {
    @Mock
    private HotelService hotelService;


    @InjectMocks
    private CreateHotelUseCase createHotelUseCase;

    @Test
    public void testCreateHotelUseCase() {
        Hotel hotel = Hotel.builder()
                .id(1L)
                .name("Test Hotel")
                .address(Address.builder().street("Test Street").zipcode("12345").country("Test Country").build())
                .rooms(Collections.singletonList(Room.builder().number(101).price(BigDecimal.valueOf(100.0)).status(RoomStatus.AVAILABLE).build()))
                .build();

        createHotelUseCase.execute(hotel);

        Mockito.verify(hotelService).createHotel(hotel);
    }

}
