package com.example.Neo4jExample.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Data
@Node
public class Accommodation {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Relationship(type = "RATED", direction = Relationship.Direction.INCOMING)
    @JsonProperty("rated")
    private List<Rated> ratings;

    @Relationship(type = "STAYED_IN", direction = Relationship.Direction.INCOMING)
    private List<User> users;
}
