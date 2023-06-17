package com.example.Neo4jExample.repository;

import com.example.Neo4jExample.model.Accommodation;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface AccommodationRepository extends Neo4jRepository<Accommodation, Long> {

    @Query("MATCH (u:User)-[:STAYED_IN]->(a:Accommodation)<-[r:RATED]-(u) WHERE u.userId IN $0 AND r.value IN [4, 5] RETURN a")
    public List<Accommodation> getHighlyRatedAccommodations(List<Long> userIds);

    @Query("MATCH (u:User)-[:STAYED_IN]->(a:Accommodation)<-[r:RATED]-(u) " +
            "WHERE a.accommodationId IN $0" +
            "WITH a, collect(r.value) AS grades " +
            "WHERE size([grade IN grades WHERE grade < 3]) < 5 " +
            "RETURN a")
    public List<Accommodation> filterOutPoorlyRated(List<Long> accommodationIds);

    @Query("MATCH (a:Accommodation)<-[r:RATED]-(:User) " +
            "WHERE a.accommodationId IN $0 " +
            "WITH a, avg(r.value) AS averageGrade " +
            "RETURN a " +
            "ORDER BY averageGrade DESC")
    List<Accommodation> sortAccommodationsByAverageGrade(List<Long> accommodationIds);

    @Query("MATCH (n:Accommodation) WHERE id(n)={0} DETACH DELETE n")
    void deleteEntityWithAllRelationships(Long id);

    Accommodation findByName(String name);

    Accommodation findByAccommodationId(long accommodationId);
}
