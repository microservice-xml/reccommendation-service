package com.example.Neo4jExample.service;

import com.example.Neo4jExample.model.Accommodation;
import com.example.Neo4jExample.model.User;
import com.example.Neo4jExample.repository.AccommodationRepository;
import com.example.Neo4jExample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final AccommodationRepository accommodationRepository;

    private final UserRepository userRepository;

    public List<User> getSimilarUsers(Long userId) {
        return userRepository.getSimilarUsers(userId);
    }

    public List<Accommodation> getHighlyRatedAccommodations(List<User> users) {
        return accommodationRepository.getHighlyRatedAccommodations(getUserIds(users));
    }

    public List<Accommodation> filterOutPoorlyRated(List<Accommodation> accommodations) {
        return accommodationRepository.filterOutPoorlyRated(getAccommodationIds(accommodations));
    }

    public List<Accommodation> sortByAverageGrade(List<Accommodation> accommodations) {
        return accommodationRepository.sortAccommodationsByAverageGrade(getAccommodationIds(accommodations));
    }
    public List<Accommodation> recommend(Long userId) {
        var similarUsers = getSimilarUsers(userId);
        var accommodations = getHighlyRatedAccommodations(similarUsers);
        accommodations = filterOutPoorlyRated(accommodations);
        return sortByAverageGrade(accommodations);
    }

    private List<Long> getAccommodationIds(List<Accommodation> accommodations) {
        return accommodations.stream().map(Accommodation::getAccommodationId).toList();
    }

    private List<Long> getUserIds(List<User> users) {
        return users.stream().map(User::getUserId).toList();
    }
}
