package com.example.lab4.service;

import com.example.lab4.config.RabbitConfig;
import com.example.lab4.dto.GradeMessage;
import com.example.lab4.entity.Course;
import com.example.lab4.entity.Grade;
import com.example.lab4.repository.CourseRepository;
import com.example.lab4.repository.GradeRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class GradeConsumer {

    private final GradeRepository gradeRepository;
    private final CourseRepository courseRepository;

    public GradeConsumer(GradeRepository gradeRepository,
                         CourseRepository courseRepository) {
        this.gradeRepository = gradeRepository;
        this.courseRepository = courseRepository;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void receiveMessage(GradeMessage msg) {
        System.out.println("===== GRADE RECEIVED =====");
        System.out.println("Student: " + msg.getStudentCode());
        System.out.println("Course:  " + msg.getCourseCode());
        System.out.println("Grade:   " + msg.getGrade());

        try {
            // 1. Găsim cursul
            Course course = courseRepository.findByCode(msg.getCourseCode());
            if (course == null) {
                System.out.println("❌ Course not found - this will go to DLQ after retries");
                // Aruncăm excepție controlată pentru DLQ
                throw new IllegalArgumentException("Unknown course code: " + msg.getCourseCode());
            }

            // 2. Păstrăm DOAR compulsory
            if (!"compulsory".equalsIgnoreCase(course.getType())) {
                System.out.println("ℹ️ Course is not compulsory, ignoring grade.");
                return; // Nu este o eroare, doar ignorăm
            }

            // 3. Salvăm în DB
            Grade grade = new Grade();
            grade.setStudentCode(msg.getStudentCode());
            grade.setCourseCode(msg.getCourseCode());
            grade.setGrade(msg.getGrade());

            gradeRepository.save(grade);
            System.out.println("✅ Grade saved to DB (compulsory course).");

        } catch (Exception e) {
            System.out.println("🚨 Processing failed: " + e.getMessage());
            System.out.println("📨 This message will be retried, then go to DLQ");
            throw e; // Re-aruncă excepția pentru retry mechanism
        }
    }
}