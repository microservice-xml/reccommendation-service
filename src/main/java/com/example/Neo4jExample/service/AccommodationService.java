package com.example.Neo4jExample.service;

import com.example.Neo4jExample.dto.NewAccommodationMessage;
import com.example.Neo4jExample.model.Accommodation;
import com.example.Neo4jExample.model.EventType;
import com.example.Neo4jExample.repository.AccommodationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;

    private final ObjectMapper objectMapper;

    public void save(Accommodation accommodation) {
        accommodationRepository.save(accommodation);
    }

    public void rollback(String name) {
        Accommodation accommodation = accommodationRepository.findByName(name);
        accommodationRepository.delete(accommodation);
    }

    public void delete(long accommodationId) {
        accommodationRepository.deleteEntityWithAllRelationships(accommodationId);
    }

}
