package com.example.hotelReservation.adapter.controller;
import com.example.hotelReservation.adapter.dto.AddressRequestDTO;
import com.example.hotelReservation.adapter.dto.HotelRequestDTO;
import com.example.hotelReservation.adapter.dto.RoomRequestDTO;
import com.example.hotelReservation.application.usecase.*;
import com.example.hotelReservation.entities.Address;
import com.example.hotelReservation.entities.Hotel;
import com.example.hotelReservation.entities.Room;
import com.example.hotelReservation.entities.RoomStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@WebMvcTest(HotelController.class)
@ExtendWith(MockitoExtension.class)
class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateHotelUseCase createHotelUseCase;
    @MockBean
    private CreateRoomUseCase createRoomUseCase;
    @MockBean
    private UpdateHotelUseCase updateHotelUseCase;
    @MockBean
    private UpdateRoomUseCase updateRoomUseCase;
    @MockBean
    private ListHotelsUseCase listHotelsUseCase;
    @MockBean
    private ListHotelRoomsUseCase listHotelRoomsUseCase;
    @MockBean
    private GetHotelUseCase getHotelUseCase;

    @Test
    void testCreateHotel() throws Exception {
        HotelRequestDTO requestDTO = buildHotelRequestDTO();

        mockMvc.perform(MockMvcRequestBuilders.post("/hotel")
                        .content(asJsonString(requestDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(createHotelUseCase).execute(any());
    }

    @Test
    void testCreateRoom() throws Exception {
        RoomRequestDTO requestDTO = buildRoomRequestDTO();

        mockMvc.perform(MockMvcRequestBuilders.post("/hotel/1/room")
                        .content(asJsonString(requestDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(createRoomUseCase).execute(eq(1L), any());
    }

    @Test
    void testUpdateHotel() throws Exception {
        HotelRequestDTO requestDTO = buildHotelRequestDTO();

        mockMvc.perform(MockMvcRequestBuilders.put("/hotel/1")
                        .content(asJsonString(requestDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        verify(updateHotelUseCase).execute(eq(1L), any());
    }

    @Test
    void testUpdateHotelRoom() throws Exception {
        RoomRequestDTO requestDTO = buildRoomRequestDTO();

        mockMvc.perform(MockMvcRequestBuilders.put("/hotel/1/room/1")
                        .content(asJsonString(requestDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        verify(updateRoomUseCase).execute(eq(1L), eq(1L), any());
    }

    @Test
    void testGetHotel() throws Exception {
        Hotel hotel = buildSampleHotel();

        when(getHotelUseCase.execute(eq(1L))).thenReturn(hotel);

        mockMvc.perform(MockMvcRequestBuilders.get("/hotel/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Sample Hotel"));

        verify(getHotelUseCase, times(1)).execute(eq(1L));
    }

    @Test
    void testListHotels() throws Exception {
        List<Hotel> hotels = Collections.singletonList(buildSampleHotel());

        when(listHotelsUseCase.execute()).thenReturn(hotels);

        mockMvc.perform(MockMvcRequestBuilders.get("/hotel/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Sample Hotel"));

        verify(listHotelsUseCase, times(1)).execute();
    }

    @Test
    void testListHotelRooms() throws Exception {
        List<Room> rooms = Collections.singletonList(buildSampleRoom());

        when(listHotelRoomsUseCase.execute(eq(1L))).thenReturn(rooms);

        mockMvc.perform(MockMvcRequestBuilders.get("/hotel/1/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].number").value(101));

        verify(listHotelRoomsUseCase, times(1)).execute(eq(1L));
    }

    private HotelRequestDTO buildHotelRequestDTO() {
        return HotelRequestDTO.builder()
                .name("Sample Hotel")
                .address(AddressRequestDTO.builder().street("Sample Street").zipcode("12345").country("Sample Country").build())
                .rooms(Collections.singletonList(buildRoomRequestDTO()))
                .build();
    }

    private RoomRequestDTO buildRoomRequestDTO() {
        return RoomRequestDTO.builder()
                .number(101)
                .price(BigDecimal.valueOf(100))
                .status(RoomStatus.AVAILABLE)
                .build();
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

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
