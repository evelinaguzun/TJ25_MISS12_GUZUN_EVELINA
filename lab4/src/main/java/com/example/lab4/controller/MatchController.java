package com.example.lab4.controller;

import com.example.lab4.service.StableMatchService;
import com.example.lab4.service.StudentRankingService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/match")
public class MatchController {

    private final StableMatchService stableMatchService;
    private final StudentRankingService studentRankingService;

    public MatchController(StableMatchService stableMatchService,
                           StudentRankingService studentRankingService) {
        this.stableMatchService = stableMatchService;
        this.studentRankingService = studentRankingService;
    }

    @PostMapping("/solve")
    public Map<String, Object> solveMatching(@RequestBody Map<String, Object> matchingProblem) {
        return stableMatchService.solveWithFallback(matchingProblem);
    }

    // ENDPOINT NOU PENTRU A TESTA DOAR RANKED MATCHING
    @PostMapping("/solve-ranked")
    public Map<String, Object> solveRankedMatching(@RequestBody Map<String, Object> matchingProblem) {
        return stableMatchService.solveWithRankedOnly(matchingProblem);
    }

    // ENDPOINT PENTRU A VEDEA SCORURILE STUDENȚILOR
    @PostMapping("/scores/{courseId}")
    public Map<String, Object> getStudentScores(@PathVariable Long courseId,
                                                @RequestBody List<String> studentCodes) {
        Map<String, Double> scores = studentRankingService.getStudentScoresForCourse(courseId, studentCodes);

        Map<String, Object> response = new HashMap<>();
        response.put("courseId", courseId);
        response.put("studentScores", scores);
        response.put("message", "Student scores based on instructor preferences");

        return response;
    }
}