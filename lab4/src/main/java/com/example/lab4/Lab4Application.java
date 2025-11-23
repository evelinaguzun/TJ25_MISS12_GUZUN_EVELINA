package com.example.lab4;

import com.example.lab4.entity.*;
import com.example.lab4.repository.*;
import net.datafaker.Faker;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableRabbit
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
            Faker faker = new Faker();
            Random rand = new Random();

            // --- CREATE ---
            // 5 studenți random
            for (int i = 1; i <= 5; i++) {
                Student s = new Student();
                s.setCode("S00" + i);
                s.setName(faker.name().fullName());
                s.setEmail(faker.internet().emailAddress());
                s.setYear(rand.nextInt(3) + 1);
                studentRepo.save(s);
            }

            // Instructori
            Instructor i1 = new Instructor();
            i1.setName(faker.name().fullName());
            i1.setEmail(faker.internet().emailAddress());
            instructorRepo.save(i1);

            Instructor i2 = new Instructor();
            i2.setName(faker.name().fullName());
            i2.setEmail(faker.internet().emailAddress());
            instructorRepo.save(i2);

            // Pachete
            Pack p1 = new Pack();
            p1.setName("Optional Pack 1");
            p1.setYear(2);
            p1.setSemester(1);
            packRepo.save(p1);

            Pack p2 = new Pack();
            p2.setName("Optional Pack 2");
            p2.setYear(3);
            p2.setSemester(2);
            packRepo.save(p2);

            // Cursuri
            Course c1 = new Course();
            c1.setType("optional");
            c1.setCode("CS201");
            c1.setAbbr("ALG");
            c1.setName("Algorithms");
            c1.setGroupCount(2);
            c1.setDescription("Algorithms and data structures");
            c1.setInstructor(i1);
            c1.setPack(p1);
            courseRepo.save(c1);

            Course c2 = new Course();
            c2.setType("compulsory");
            c2.setCode("CS301");
            c2.setAbbr("DBS");
            c2.setName("Databases");
            c2.setGroupCount(1);
            c2.setDescription("Introduction to SQL and databases");
            c2.setInstructor(i2);
            c2.setPack(p2);
            courseRepo.save(c2);

            System.out.println(" CREATE: Cursurile au fost adăugate în baza de date.");

            // --- READ ---
            System.out.println("\n READ: Lista cursurilor existente în DB:");
            List<Course> courses = courseRepo.findAll();
            courses.forEach(c ->
                    System.out.println(" - " + c.getName() + " (" + c.getCode() + ")")
            );

            // --- UPDATE ---
            if (!courses.isEmpty()) {
                Course courseToUpdate = courses.get(0);
                String oldName = courseToUpdate.getName();
                courseToUpdate.setName(oldName + " [UPDATED]");
                courseRepo.save(courseToUpdate);
                System.out.println("\n UPDATE: Cursul '" + oldName + "' a fost actualizat la '" + courseToUpdate.getName() + "'");
            }

            // --- DELETE ---
            if (courses.size() > 1) {
                Course courseToDelete = courses.get(1);
                courseRepo.delete(courseToDelete);
                System.out.println("\n DELETE: Cursul '" + courseToDelete.getName() + "' a fost șters din DB.");
            }

            System.out.println("\n Toate operațiile CRUD au fost testate cu succes!");
        };
    }
}

