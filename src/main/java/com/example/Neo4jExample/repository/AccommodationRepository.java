package com.example.Neo4jExample.repository;

import com.example.Neo4jExample.model.Accommodation;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface AccommodationRepository extends Neo4jRepository<Accommodation, Long> {
}
