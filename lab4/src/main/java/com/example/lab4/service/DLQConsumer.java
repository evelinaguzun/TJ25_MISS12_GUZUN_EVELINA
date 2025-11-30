package com.example.lab4.service;

import com.example.lab4.config.RabbitConfig;
import com.example.lab4.dto.GradeMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class DLQConsumer {

    @RabbitListener(queues = RabbitConfig.DLQ)
    public void handleFailedMessage(GradeMessage failedMessage) {
        System.out.println("====== 📨 DLQ MESSAGE RECEIVED ======");
        System.out.println("🚨 Final failure after all retries:");
        System.out.println("   Student: " + failedMessage.getStudentCode());
        System.out.println("   Course:  " + failedMessage.getCourseCode());
        System.out.println("   Grade:   " + failedMessage.getGrade());
        System.out.println("   Reason:  Unknown course code");
        System.out.println("======================================");
    }
}