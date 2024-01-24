package com.example.hotelReservation.infrastructure.persistence.jpa;

import com.example.hotelReservation.infrastructure.persistence.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelJpaRepository extends JpaRepository<HotelEntity, Long> {
    HotelEntity findFirstByName(String name);
}
