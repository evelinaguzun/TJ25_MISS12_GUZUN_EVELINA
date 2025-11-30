package com.example.lab4.controller;

import com.example.lab4.entity.Grade;
import com.example.lab4.entity.Course;
import com.example.lab4.repository.CourseRepository;
import com.example.lab4.repository.GradeRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeController {

    private final GradeRepository gradeRepository;
    private final CourseRepository courseRepository;

    public GradeController(GradeRepository gradeRepository,
                           CourseRepository courseRepository) {
        this.gradeRepository = gradeRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    @GetMapping("/student/{code}")
    public List<Grade> getGradesForStudent(@PathVariable String code) {
        return gradeRepository.findByStudentCode(code);
    }

    @GetMapping("/course/{code}")
    public List<Grade> getGradesForCourse(@PathVariable String code) {
        return gradeRepository.findByCourseCode(code);
    }

    // CSV format: studentCode,courseCode,grade
    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<?> uploadCsv(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Empty file");
        }

        int imported = 0;
        int skipped = 0;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;

                // sari peste header dacă există
                if (firstLine && line.toLowerCase().contains("student")) {
                    firstLine = false;
                    continue;
                }
                firstLine = false;

                String[] parts = line.split(",");
                if (parts.length < 3) {
                    skipped++;
                    continue;
                }

                String studentCode = parts[0].trim();
                String courseCode = parts[1].trim();
                int gradeValue;
                try {
                    gradeValue = Integer.parseInt(parts[2].trim());
                } catch (NumberFormatException e) {
                    skipped++;
                    continue;
                }

                Course course = courseRepository.findByCode(courseCode);
                if (course == null) {
                    // aici ar fi un caz de „failed”, dar pentru CSV doar îl sărim
                    skipped++;
                    continue;
                }

                if (!"compulsory".equalsIgnoreCase(course.getType())) {
                    skipped++;
                    continue;
                }

                Grade g = new Grade();
                g.setStudentCode(studentCode);
                g.setCourseCode(courseCode);
                g.setGrade(gradeValue);
                gradeRepository.save(g);
                imported++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("Error reading CSV: " + e.getMessage());
        }

        return ResponseEntity.ok("Imported: " + imported + " grades, skipped: " + skipped);
    }
}
