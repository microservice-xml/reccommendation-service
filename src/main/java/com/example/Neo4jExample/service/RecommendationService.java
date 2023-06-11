package com.example.Neo4jExample.service;

import com.example.Neo4jExample.model.Accommodation;
import com.example.Neo4jExample.model.Rated;
import com.example.Neo4jExample.model.User;
import com.example.Neo4jExample.repository.AccommodationRepository;
import com.example.Neo4jExample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Accommodation createRelationshipRated(Rated rated, long userId, long accommodationId) {
        Accommodation accommodation = accommodationRepository.findByAccommodationId(accommodationId);
        User user = userRepository.findByUserId(userId);
        rated.setUser(user);
        accommodation.addRatedRelationship(rated);
        accommodationRepository.save(accommodation);
        return accommodation;
    }

    public Accommodation createRelationshipStayed(long userId, long accommodationId) {
        Accommodation accommodation = accommodationRepository.findByAccommodationId(accommodationId);
        User user = userRepository.findByUserId(userId);
        accommodation.addStayedRelationship(user);
        accommodationRepository.save(accommodation);
        return accommodation;
    }

    public Accommodation rollbackRated(long userId, long accommodationId) {
        Accommodation accommodation = accommodationRepository.findByAccommodationId(accommodationId);
        accommodation.getRatings().removeIf(rated -> rated.getUser().getUserId() == userId);
        accommodationRepository.save(accommodation);
        return accommodation;
    }

    public Accommodation rollbackStayed(long userId, long accommodationId) {
        Accommodation accommodation = accommodationRepository.findByAccommodationId(accommodationId);
        accommodation.getUsers().removeIf(user -> user.getUserId() == userId);
        accommodationRepository.save(accommodation);
        return accommodation;
    }

    public Accommodation changeRate(long userId, long accommodationId, int value) {
        Accommodation accommodation = accommodationRepository.findByAccommodationId(accommodationId);
        Optional<Rated> rated = accommodation.getRatings().stream().filter(r -> r.getUser().getUserId() == userId).findFirst();
        if (rated.isPresent()) {
            rated.get().setValue(value);
            accommodationRepository.save(accommodation);
        }
        return accommodation;
    }

    public Accommodation deleteRate(long userId, long accommodationId) {
        Accommodation accommodation = accommodationRepository.findByAccommodationId(accommodationId);
        accommodation.getRatings().removeIf(rated -> rated.getUser().getUserId() == userId);
        accommodationRepository.save(accommodation);
        return accommodation;
    }
}
