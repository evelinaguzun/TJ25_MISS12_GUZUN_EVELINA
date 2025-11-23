package com.example.lab4.service;

import com.example.lab4.dto.GradeMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class GradeConsumer {

    @RabbitListener(queues = "grade.queue")
    public void receiveMessage(GradeMessage msg) {
        System.out.println("===== GRADE RECEIVED =====");
        System.out.println("Student: " + msg.getStudentCode());
        System.out.println("Course:  " + msg.getCourseCode());
        System.out.println("Grade:   " + msg.getGrade());
    }
}
