package com.example.lab4.repository;

import com.example.lab4.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudentCode(String studentCode);
    List<Grade> findByCourseCode(String courseCode);


    List<Grade> findByStudentCodeAndCourseCode(String studentCode, String courseCode);
}