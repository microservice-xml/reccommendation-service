package com.example.Neo4jExample.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;

@Data
@Node
public class Accommodation {

    @Id
    @GeneratedValue
    private Long id;

    @Property
    private Long accommodationId;

    private String name;

    @Relationship(type = "RATED", direction = Relationship.Direction.INCOMING)
    @JsonProperty("rated")
    private List<Rated> ratings;

    @Relationship(type = "STAYED_IN", direction = Relationship.Direction.INCOMING)
    private List<User> users;
}
