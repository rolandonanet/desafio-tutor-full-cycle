package com.example.hotelReservation.infrastructure.config;

import com.example.hotelReservation.infrastructure.persistence.seed.DataSeeder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!test")
public class SeederConfig {

    @Value("${seed.data.enabled}")
    private boolean seedDataEnabled;

    @Bean
    public CommandLineRunner commandLineRunner(DataSeeder dataSeeder) {
        if (seedDataEnabled) {
            return args -> dataSeeder.seedData();
        }
        return args -> {};
    }
}