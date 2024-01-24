package com.example.hotelReservation.infrastructure.persistence.repository;

import com.example.hotelReservation.adapter.gateway.repository.HotelGatewayRepository;
import com.example.hotelReservation.entities.Hotel;
import com.example.hotelReservation.infrastructure.persistence.entity.HotelEntity;
import com.example.hotelReservation.infrastructure.persistence.jpa.HotelJpaRepository;
import com.example.hotelReservation.infrastructure.persistence.mapper.HotelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class HotelRepositoryImpl implements HotelGatewayRepository {

    private final HotelJpaRepository hotelJpaRepository;

    @Override
    public Optional<Hotel> findByName(String name) {
        HotelEntity hotelEntity = hotelJpaRepository.findFirstByName(name);
        return Optional.ofNullable(HotelMapper.map.entityToHotel(hotelEntity));
    }
    @Override
    public Boolean exists(Long hotelId) {
       return hotelJpaRepository.existsById(hotelId);
    }

    @Override
    public void save(Hotel hotel) {
        HotelEntity hotelToSave = HotelMapper.map.hotelToEntity(hotel);
        hotelJpaRepository.save(hotelToSave);
    }

    @Override
    public List<Hotel> list() {
        List<HotelEntity> hotels = hotelJpaRepository.findAll();
        return HotelMapper.map.entitiesToHotels(hotels);
    }

    @Override
    public Hotel findById(Long hotelId) {
        HotelEntity hotelEntity = hotelJpaRepository.findById(hotelId).orElse(null);
        return HotelMapper.map.entityToHotel(hotelEntity);
    }
}
