package com.example.Neo4jExample.controller;

import com.example.Neo4jExample.model.Accommodation;
import com.example.Neo4jExample.model.User;
import com.example.Neo4jExample.repository.AccommodationRepository;
import com.example.Neo4jExample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/song")
@RequiredArgsConstructor
public class SongController {

    private final AccommodationRepository accommodationRepository;

    private final UserRepository userRepository;

    @GetMapping("/accommodation")
    public List<Accommodation> getAll() {
        var acc = accommodationRepository.findAll();
        return acc;
    }

    @GetMapping("/user")
    public List<User> getAllUsers() {
        var acc = userRepository.findAll();
        return acc;
    }

    @PostMapping("/")
    public User addUser() {
        User user = new User();
        user.setName("Djordje Jovanovic");
        userRepository.save(user);
        return user;
    }
}
