package com.example.hotelReservation.adapter.gateway.messaging;


public interface RabbitMQProducerGateway {
    void sendMessage(Object message);
}
