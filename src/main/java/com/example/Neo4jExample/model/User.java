package com.example.Neo4jExample.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;

@Data
@Node
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Property
    private Long userId;

    @Property
    private String name;

    public User() {}

    public User(Long userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}
