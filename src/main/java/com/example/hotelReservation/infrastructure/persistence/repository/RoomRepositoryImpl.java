package com.example.hotelReservation.infrastructure.persistence.repository;

import com.example.hotelReservation.adapter.gateway.repository.RoomGatewayRepository;
import com.example.hotelReservation.infrastructure.persistence.jpa.RoomJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class RoomRepositoryImpl implements RoomGatewayRepository {

    private final RoomJpaRepository roomJpaRepository;


}
