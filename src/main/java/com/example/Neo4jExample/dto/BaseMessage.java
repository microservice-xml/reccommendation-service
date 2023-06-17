package com.example.Neo4jExample.dto;

import com.example.Neo4jExample.model.Accommodation;
import com.example.Neo4jExample.model.EventType;
import com.example.Neo4jExample.model.Rated;
import com.example.Neo4jExample.model.User;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Data;

@Data
public class BaseMessage {

    protected EventType type;

    private long accommodationId;

    private String name;

    private long userId;

    private int grade;

    public BaseMessage() {}

    public BaseMessage(EventType eventType) {}

    public User produceUser() {
        return new User(userId, name);
    }

    public Accommodation produceAccommodation() {
        return new Accommodation(accommodationId, name);
    }

    public Rated produceGrade() {
        return new Rated(grade);
    }
}
