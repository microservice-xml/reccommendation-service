package com.example.Neo4jExample.service;

import com.example.Neo4jExample.dto.BaseMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListenerService {

    private final AccommodationService accommodationService;

    private final UserService userService;

    private final RecommendationService recommendationService;

    private final ObjectMapper objectMapper;

    @RabbitListener(queues = {"recommendationQueue"})
    public void consume(String message){
        try {
            BaseMessage baseMessage = objectMapper.readValue(message, BaseMessage.class);
            correspond(baseMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void correspond(BaseMessage baseMessage) throws Exception{
        switch (baseMessage.getType()) {
            case USER_CREATED -> {
                userService.save(baseMessage.produceUser());
            }
            case ACCOMMODATION_CREATED -> {
                accommodationService.save(baseMessage.produceAccommodation());
            }
            case USER_CREATE_FAILED -> {
                userService.rollback(baseMessage.getName());
            }
            case ACCOMMODATION_CREATE_FAILED -> {
                accommodationService.rollback(baseMessage.getName());
            }
            case RATE_CREATED -> {
                recommendationService.createRelationshipRated(baseMessage.produceGrade(), baseMessage.getUserId(), baseMessage.getAccommodationId());
            }
            case USER_STAYED -> {
                recommendationService.createRelationshipStayed(baseMessage.getUserId(), baseMessage.getAccommodationId());
            }
            case RATE_CREATE_FAILED -> {
                recommendationService.rollbackRated(baseMessage.getUserId(), baseMessage.getAccommodationId());
            }
            case USER_STAY_FAILED, RESERVATION_CANCELED -> {
                recommendationService.rollbackStayed(baseMessage.getUserId(), baseMessage.getAccommodationId());
            }
            case ACCOMMODATION_DELETED -> {
                accommodationService.delete(baseMessage.getAccommodationId());
            }
            case USER_DELETED -> {
                userService.delete(baseMessage.getUserId());
            }
            case RATE_CHANGED -> {
                recommendationService.changeRate(baseMessage.getUserId(), baseMessage.getAccommodationId(), baseMessage.getGrade());
            }
            case RATE_DELETED -> {
                recommendationService.deleteRate(baseMessage.getUserId(), baseMessage.getAccommodationId());
            }
        }
    }
}
