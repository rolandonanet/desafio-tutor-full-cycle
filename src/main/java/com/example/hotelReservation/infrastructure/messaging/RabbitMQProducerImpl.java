package com.example.hotelReservation.infrastructure.messaging;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.hotelReservation.adapter.gateway.messaging.RabbitMQProducerGateway;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RabbitMQProducerImpl implements RabbitMQProducerGateway {

    private final AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingkey}")
    private String routingkey;

    @SneakyThrows
    @Override
    public void sendMessage(Object message) {
        ObjectMapper objectMapper = new ObjectMapper();
        String hotelJson = objectMapper.writeValueAsString(message);
        amqpTemplate.convertAndSend(exchange, routingkey, hotelJson);
        System.out.println("Mensagem enviada: " + message);
    }
}
