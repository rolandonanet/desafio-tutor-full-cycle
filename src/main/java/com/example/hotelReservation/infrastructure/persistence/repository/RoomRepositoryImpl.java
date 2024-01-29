package com.example.hotelReservation.infrastructure.persistence.repository;

import com.example.hotelReservation.infrastructure.persistence.jpa.RoomJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class RoomRepositoryImpl {

    private final RoomJpaRepository roomJpaRepository;


}
