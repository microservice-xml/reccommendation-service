package com.example.Neo4jExample.controller;

import com.example.Neo4jExample.model.Accommodation;
import com.example.Neo4jExample.model.User;
import com.example.Neo4jExample.repository.AccommodationRepository;
import com.example.Neo4jExample.repository.UserRepository;
import com.example.Neo4jExample.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/recommend")
@RequiredArgsConstructor
public class RecommendationController {

    private final AccommodationRepository accommodationRepository;

    private final RecommendationService recommendationService;

    private final UserRepository userRepository;

    @GetMapping("/accommodation")
    public List<Accommodation> getAll() {
        var acc = accommodationRepository.findAll();
        return acc;
    }

    @GetMapping("/recommend/{userId}")
    public List<Accommodation> recommend(@PathVariable("userId") Long userId) {
        return recommendationService.recommend(userId);
    }

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
