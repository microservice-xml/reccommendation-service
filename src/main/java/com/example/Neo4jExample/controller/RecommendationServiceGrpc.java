package com.example.Neo4jExample.controller;

import com.example.Neo4jExample.model.Accommodation;
import com.example.Neo4jExample.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;

@RequiredArgsConstructor
@GrpcService
public class RecommendationServiceGrpc extends communication.RecommendationServiceGrpc.RecommendationServiceImplBase {

    private final RecommendationService recommendationService;

    @Override
    public void recommend(communication.RecommendationRequest request,
                          io.grpc.stub.StreamObserver<communication.RecommendationResponse> responseObserver) {

        var accommodations = recommendationService.recommend(request.getUserId());
        List<Long> ids = accommodations.stream().map(Accommodation::getAccommodationId).toList();
        communication.RecommendationResponse res = communication.RecommendationResponse.newBuilder().addAllAccommodationIds(ids).build();

        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }
}
