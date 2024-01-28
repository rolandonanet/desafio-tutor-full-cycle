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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UpdateHotelUseCaseTest {

    @Mock
    private HotelService hotelService;

    @InjectMocks
    private UpdateHotelUseCase updateHotelUseCase;

    @Test
    void testUpdateHotel() {
        Long hotelId = 1L;
        Hotel updatedHotel = buildSampleHotel();

        updateHotelUseCase.execute(hotelId, updatedHotel);

        verify(hotelService).updateHotel(eq(hotelId), eq(updatedHotel));
    }

    private Hotel buildSampleHotel() {
        return Hotel.builder()
                .id(1L)
                .name("Sample Hotel")
                .address(Address.builder().street("Sample Street").zipcode("12345").country("Sample Country").build())
                .rooms(Collections.singletonList(buildSampleRoom()))
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
