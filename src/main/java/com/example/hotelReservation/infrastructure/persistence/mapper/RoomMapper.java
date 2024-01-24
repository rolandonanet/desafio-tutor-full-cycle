package com.example.hotelReservation.infrastructure.persistence.mapper;

import com.example.hotelReservation.entities.Room;
import com.example.hotelReservation.infrastructure.persistence.entity.RoomEntity;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface RoomMapper {
    RoomMapper map = Mappers.getMapper(RoomMapper.class);
    RoomEntity roomToEntity(Room room);
    Room entityToRoom(RoomEntity roomEntity);
    List<Room> entityListToRooms(List<RoomEntity> roomEntities);
}
