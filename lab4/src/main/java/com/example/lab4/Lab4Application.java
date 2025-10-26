package com.example.lab4;

import com.example.lab4.entity.*;
import com.example.lab4.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lab4Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab4Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(StudentRepository studentRepo,
                                  InstructorRepository instructorRepo,
                                  PackRepository packRepo,
                                  CourseRepository courseRepo) {
        return args -> {


            Student s = new Student();
            s.setCode("S001");
            s.setName("Guzun Evelina");
            s.setEmail("guzun.evelina@gmail.com");
            s.setYear(2);
            studentRepo.save(s);


            Instructor i = new Instructor();
            i.setName("Dr. Andrei Ionescu");
            i.setEmail("andrei.ionescu@example.com");
            instructorRepo.save(i);


            Pack p = new Pack();
            p.setYear(2);
            p.setSemester(1);
            p.setName("Optional Pack 1");
            packRepo.save(p);


            Course c = new Course();
            c.setType("optional");
            c.setCode("CS202");
            c.setAbbr("ALG");
            c.setName("Advanced Algorithms");
            c.setGroupCount(2);
            c.setDescription("Algorithms and data structures.");
            c.setInstructor(i);
            c.setPack(p);
            courseRepo.save(c);

            System.out.println("Datele de test au fost adăugate cu succes!");
        };
    }
}
