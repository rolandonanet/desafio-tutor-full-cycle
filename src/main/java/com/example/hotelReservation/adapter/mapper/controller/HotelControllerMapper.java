package com.example.hotelReservation.adapter.mapper.controller;

import com.example.hotelReservation.adapter.dto.HotelRequestDTO;
import com.example.hotelReservation.adapter.dto.HotelResponseDTO;
import com.example.hotelReservation.entities.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface HotelControllerMapper {
    HotelControllerMapper map = Mappers.getMapper(HotelControllerMapper.class);
    Hotel hotelRequestDtoToHotel(HotelRequestDTO hotelRequestDTO);
    HotelRequestDTO hotelToHotelRequestDto(Hotel hotel);
    Hotel hotelResponsetDtoToHotel(HotelResponseDTO hotelResponseDTO);
    HotelResponseDTO hotelToHotelResponseDto(Hotel hotel);
    List<HotelResponseDTO> listHotelToListHotelResponseDto(List<Hotel> hotels);

}
