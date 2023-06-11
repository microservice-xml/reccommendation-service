package com.example.Neo4jExample.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Node
@AllArgsConstructor
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

    public Accommodation() {
        ratings = new ArrayList<>();
        users = new ArrayList<>();
    }

    public Accommodation(Long accommodationId, String name) {
        this.accommodationId = accommodationId;
        this.name = name;
        ratings = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addRatedRelationship(Rated rated) {
        ratings.add(rated);
    }

    public void addStayedRelationship(User user) {
        users.add(user);
    }
}
