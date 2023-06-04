package com.example.Neo4jExample.repository;

import com.example.Neo4jExample.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface UserRepository extends Neo4jRepository<User, Long> {
}
