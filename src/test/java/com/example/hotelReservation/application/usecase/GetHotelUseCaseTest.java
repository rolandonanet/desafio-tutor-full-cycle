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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetHotelUseCaseTest {

    @Mock
    private HotelService hotelService;

    @InjectMocks
    private GetHotelUseCase getHotelUseCase;

    @Test
    void testGetHotel() {
        Long hotelId = 1L;
        Hotel expectedHotel = buildSampleHotel();

        when(hotelService.getHotel(eq(hotelId))).thenReturn(expectedHotel);

        Hotel actualHotel = getHotelUseCase.execute(hotelId);

        verify(hotelService).getHotel(eq(hotelId));

        assertEquals(expectedHotel, actualHotel);
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
