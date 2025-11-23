package com.example.lab4.controller;

import com.example.lab4.entity.Course;
import com.example.lab4.repository.CourseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseRepository courseRepo;

    public CourseController(CourseRepository courseRepo) {
        this.courseRepo = courseRepo;
    }

    // GET all courses
    @GetMapping
    public List<Course> getAll() {
        return courseRepo.findAll();
    }

    // GET by id
    @GetMapping("/{id}")
    public Optional<Course> getById(@PathVariable Long id) {
        return courseRepo.findById(id);
    }
}
