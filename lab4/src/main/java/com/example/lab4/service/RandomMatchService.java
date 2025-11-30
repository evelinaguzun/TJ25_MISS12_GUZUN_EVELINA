package com.example.lab4.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class RandomMatchService {

    public Map<String, Object> generateRandomMatching(List<String> students,
                                                      List<String> courses,
                                                      Map<String, Integer> capacities) {
        Map<String, String> matches = new HashMap<>();
        List<String> availableStudents = new ArrayList<>(students);
        Collections.shuffle(availableStudents);

        for (String course : courses) {
            int capacity = capacities.getOrDefault(course, 1);
            for (int i = 0; i < capacity && !availableStudents.isEmpty(); i++) {
                String student = availableStudents.remove(0);
                matches.put(student, course);
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("matches", matches);
        response.put("stable", false);
        response.put("message", "Random matching generated");
        response.put("algorithm", "random");

        return response;
    }
}