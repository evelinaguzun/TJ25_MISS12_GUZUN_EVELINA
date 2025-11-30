package com.example.lab4.service;

import com.example.lab4.entity.Course;
import com.example.lab4.entity.Grade;
import com.example.lab4.entity.InstructorPreference;
import com.example.lab4.repository.CourseRepository;
import com.example.lab4.repository.GradeRepository;
import com.example.lab4.repository.InstructorPreferenceRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentRankingService {

    private final GradeRepository gradeRepository;
    private final InstructorPreferenceRepository instructorPreferenceRepository;
    private final CourseRepository courseRepository;

    public StudentRankingService(GradeRepository gradeRepository,
                                 InstructorPreferenceRepository instructorPreferenceRepository,
                                 CourseRepository courseRepository) {
        this.gradeRepository = gradeRepository;
        this.instructorPreferenceRepository = instructorPreferenceRepository;
        this.courseRepository = courseRepository;
    }

    public List<String> rankStudentsForCourse(Long courseId, List<String> studentCodes) {
        // 1. Obține preferințele cursului
        List<InstructorPreference> preferences = instructorPreferenceRepository.findByCourseId(courseId);

        if (preferences.isEmpty()) {
            // Dacă nu există preferințe, returnează studenții în ordinea originală
            return new ArrayList<>(studentCodes);
        }

        // 2. Calculează weighted average pentru fiecare student
        Map<String, Double> studentScores = new HashMap<>();

        for (String studentCode : studentCodes) {
            double weightedAverage = calculateWeightedAverage(studentCode, preferences);
            studentScores.put(studentCode, weightedAverage);
        }

        // 3. Sortează studenții descrescător după weighted average
        return studentScores.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private double calculateWeightedAverage(String studentCode, List<InstructorPreference> preferences) {
        double totalScore = 0.0;
        double totalWeight = 0.0;

        for (InstructorPreference pref : preferences) {
            String compulsoryAbbr = pref.getCompulsoryCourseAbbr();
            int weight = pref.getPercentage();

            // găsește cursul după abreviere pentru a obține codul
            Course compulsoryCourse = courseRepository.findByAbbr(compulsoryAbbr);
            if (compulsoryCourse != null) {
                String courseCode = compulsoryCourse.getCode();

                // Găsește nota studentului la cursul compulsory
                List<Grade> grades = gradeRepository.findByStudentCodeAndCourseCode(studentCode, courseCode);
                if (!grades.isEmpty()) {
                    Grade grade = grades.get(0); // Ia prima notă
                    totalScore += grade.getGrade() * weight;
                    totalWeight += weight;
                    System.out.println("Student " + studentCode + " - " + courseCode + ": " + grade.getGrade() + " * " + weight + "%");
                } else {
                    System.out.println("Student " + studentCode + " - No grade found for " + courseCode);
                }
            } else {
                System.out.println("Course not found for abbr: " + compulsoryAbbr);
            }
        }

        double result = totalWeight > 0 ? totalScore / totalWeight : 0.0;
        System.out.println("Student " + studentCode + " - Final score: " + result);
        return result;
    }

    // Metodă pentru a obține scorurile tuturor studenților (pentru debugging)
    public Map<String, Double> getStudentScoresForCourse(Long courseId, List<String> studentCodes) {
        List<InstructorPreference> preferences = instructorPreferenceRepository.findByCourseId(courseId);
        Map<String, Double> scores = new HashMap<>();

        for (String studentCode : studentCodes) {
            double weightedAverage = calculateWeightedAverage(studentCode, preferences);
            scores.put(studentCode, weightedAverage);
        }

        return scores;
    }
}