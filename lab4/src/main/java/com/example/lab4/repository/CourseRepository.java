package com.example.lab4.repository;

import com.example.lab4.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c WHERE c.type = 'optional'")
    List<Course> findAllOptionalCourses();

    Course findByCode(String code);

    Course findByAbbr(String abbr);
}
