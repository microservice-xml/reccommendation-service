package com.example.Neo4jExample.dto;

import com.example.Neo4jExample.model.EventType;
import com.example.Neo4jExample.model.User;

public class NewUserMessage extends BaseMessage{

    private long userId;

    private String name;

    public NewUserMessage() {
        super();
    }

    public NewUserMessage(long userId, String name) {
        super();
        this.userId = userId;
        this.name = name;
    }

    public NewUserMessage(EventType eventType, long userId, String name) {
        super(eventType);
        this.userId = userId;
        this.name = name;
    }

    public User produce() {
        return new User(userId, name);
    }
}
