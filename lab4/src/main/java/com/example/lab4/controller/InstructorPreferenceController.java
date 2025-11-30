package com.example.lab4.controller;

import com.example.lab4.entity.InstructorPreference;
import com.example.lab4.repository.InstructorPreferenceRepository;
import com.example.lab4.repository.CourseRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/instructor-preferences")
public class InstructorPreferenceController {

    private final InstructorPreferenceRepository preferenceRepository;
    private final CourseRepository courseRepository;

    public InstructorPreferenceController(InstructorPreferenceRepository preferenceRepository,
                                          CourseRepository courseRepository) {
        this.preferenceRepository = preferenceRepository;
        this.courseRepository = courseRepository;
    }

    @PostMapping("/course/{courseId}")
    public InstructorPreference addPreference(@PathVariable Long courseId,
                                              @RequestBody Map<String, Object> preferenceData) {
        var course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        InstructorPreference preference = new InstructorPreference();
        preference.setCourse(course);
        preference.setCompulsoryCourseAbbr((String) preferenceData.get("compulsoryCourseAbbr"));
        preference.setPercentage((Integer) preferenceData.get("percentage"));

        return preferenceRepository.save(preference);
    }

    @GetMapping("/course/{courseId}")
    public List<InstructorPreference> getCoursePreferences(@PathVariable Long courseId) {
        return preferenceRepository.findByCourseId(courseId);
    }
}
