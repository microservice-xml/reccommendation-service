package com.example.Neo4jExample.dto;

import com.example.Neo4jExample.model.Accommodation;
import com.example.Neo4jExample.model.EventType;
import com.example.Neo4jExample.model.Rated;
import com.example.Neo4jExample.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class NewAccommodationMessage extends BaseMessage{

    private long accommodationId;

    private String name;

    private EventType type;

    public Accommodation produce() {
        return new Accommodation(accommodationId, name);
    }

    public NewAccommodationMessage() {}

    public NewAccommodationMessage(long accommodationId, String name, EventType type) {
        super(type);
        this.accommodationId = accommodationId;
        this.name = name;
    }

    public NewAccommodationMessage(long accId, String nm){
        accommodationId = accId;
        nm = name;
    }
}
