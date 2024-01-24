//package com.example.hotelReservation.adapter.controller;
//
//import com.example.hotelReservation.entities.Reservation;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/book/{hotelId}")
//public class ReservationController {
//
//    private final ReservationUseCase reservationUseCase;
//
//    @PostMapping
//    public void bookRoom(@PathVariable Long hotelId, @RequestBody Reservation reservation) {
//        reservationUseCase.bookRoom(hotelId, reservation);
//    }
//
////    @GetMapping
////    public List<RoomStatusDTO> getRoomStatus(@PathVariable Long hotelId) {
////        return reservationUseCase.getRoomStatus(hotelId);
////    }
//
//
//}
