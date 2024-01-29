package com.example.hotelReservation.adapter.mapper.controller;

import com.example.hotelReservation.adapter.dto.BookRequestDTO;
import com.example.hotelReservation.adapter.dto.HotelRequestDTO;
import com.example.hotelReservation.entities.Book;
import org.mapstruct.factory.Mappers;

public interface BookControllerMapper {
    BookControllerMapper map = Mappers.getMapper(BookControllerMapper.class);

    Book bookRequestDtoToBook(BookRequestDTO bookRequestDTO);
}
