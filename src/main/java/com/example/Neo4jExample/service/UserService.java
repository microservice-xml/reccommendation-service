package com.example.Neo4jExample.service;

import com.example.Neo4jExample.model.User;
import com.example.Neo4jExample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    public void rollback(String name) {
        User user = userRepository.findByName(name);
        if (user == null) return;
        userRepository.delete(user);
    }

    public void delete(long userId){
        userRepository.deleteEntityWithAllRelationships(userId);
    }
}
