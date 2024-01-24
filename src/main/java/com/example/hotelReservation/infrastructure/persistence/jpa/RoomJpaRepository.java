package com.example.hotelReservation.infrastructure.persistence.jpa;

import com.example.hotelReservation.infrastructure.persistence.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomJpaRepository extends JpaRepository<HotelEntity, Long> {

}
