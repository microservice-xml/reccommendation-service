package com.example.Neo4jExample.repository;

import com.example.Neo4jExample.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface UserRepository extends Neo4jRepository<User, Long> {

    @Query("MATCH (a:Accommodation)<-[:STAYED_IN]-(u:User {userId: $0})-[r1:RATED]->(a)<-[r2:RATED]-(otherUser:User) WHERE r1.value = r2.value OR r1.value - 1 = r2.value OR r1.value + 1 = r2.value RETURN otherUser")
    public List<User> getSimilarUsers(Long userId);
}
