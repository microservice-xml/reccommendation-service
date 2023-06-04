package com.example.Neo4jExample.model;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.*;

@Data
@RelationshipProperties
public class Rated {

    @Id
    @GeneratedValue
    private Long id;

    @Property("value")
    private int value;

    @TargetNode
    private User user;
}
