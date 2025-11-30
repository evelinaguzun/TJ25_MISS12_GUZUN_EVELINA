package com.example.lab4.service;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@Service
public class StableMatchService {

    private final StableMatchClient stableMatchClient;
    private final RandomMatchService randomMatchService;
    private final RankedMatchService rankedMatchService;

    public StableMatchService(StableMatchClient stableMatchClient,
                              RandomMatchService randomMatchService,
                              RankedMatchService rankedMatchService) {
        this.stableMatchClient = stableMatchClient;
        this.randomMatchService = randomMatchService;
        this.rankedMatchService = rankedMatchService;
    }

    @Retryable(
            value = {ResourceAccessException.class, TimeoutException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000, multiplier = 2)
    )
    public Map<String, Object> solveWithRetry(Map<String, Object> matchingProblem) {
        return stableMatchClient.solveMatching(matchingProblem);
    }

    public Map<String, Object> solveWithFallback(Map<String, Object> matchingProblem) {
        try {
            return solveWithRetry(matchingProblem);
        } catch (Exception e) {
            System.out.println("StableMatch service failed, using RANKED fallback: " + e.getMessage());


            return rankedMatchService.generateRankedMatching(
                    (List<String>) matchingProblem.get("students"),
                    (List<String>) matchingProblem.get("courses"),
                    (Map<String, Integer>) matchingProblem.get("courseCapacities")
            );
        }
    }


    public Map<String, Object> solveWithRankedOnly(Map<String, Object> matchingProblem) {
        return rankedMatchService.generateRankedMatching(
                (List<String>) matchingProblem.get("students"),
                (List<String>) matchingProblem.get("courses"),
                (Map<String, Integer>) matchingProblem.get("courseCapacities")
        );
    }
}
