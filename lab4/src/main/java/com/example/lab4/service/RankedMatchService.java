package com.example.lab4.service;

import com.example.lab4.entity.Course;
import com.example.lab4.repository.CourseRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class RankedMatchService {

    private final StudentRankingService rankingService;
    private final CourseRepository courseRepository;

    public RankedMatchService(StudentRankingService rankingService,
                              CourseRepository courseRepository) {
        this.rankingService = rankingService;
        this.courseRepository = courseRepository;
    }

    public Map<String, Object> generateRankedMatching(List<String> students,
                                                      List<String> courses,
                                                      Map<String, Integer> capacities) {

        Map<String, String> matches = new HashMap<>();
        List<String> availableStudents = new ArrayList<>(students);

        for (String courseCode : courses) {
            // 1. Găsește ID-ul cursului pe baza codului
            Long courseId = getCourseIdByCode(courseCode);
            if (courseId == null) {
                System.out.println("Course not found for code: " + courseCode);
                continue;
            }

            // 2. Sortează studenții disponibili pentru acest curs
            List<String> rankedStudents = rankingService.rankStudentsForCourse(courseId, availableStudents);

            // 3. Asignează studenții cei mai buni până se umple capacitatea
            int capacity = capacities.getOrDefault(courseCode, 1);
            int assigned = 0;

            for (String student : rankedStudents) {
                if (assigned >= capacity) break;
                if (availableStudents.contains(student) && !matches.containsKey(student)) {
                    matches.put(student, courseCode);
                    availableStudents.remove(student);
                    assigned++;
                }
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("matches", matches);
        response.put("stable", false);
        response.put("message", "Ranked matching based on instructor preferences");
        response.put("algorithm", "ranked");

        return response;
    }

    private Long getCourseIdByCode(String courseCode) {

        Course course = courseRepository.findByCode(courseCode);
        return course != null ? course.getId() : null;
    }
}