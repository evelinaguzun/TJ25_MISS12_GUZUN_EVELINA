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
                                  CourseRepository courseRepo,
                                  GradeRepository gradeRepo) {
        return args -> {
            Faker faker = new Faker();
            Random rand = new Random();

            // Șterge datele vechi
            gradeRepo.deleteAll();
            courseRepo.deleteAll();
            studentRepo.deleteAll();
            instructorRepo.deleteAll();
            packRepo.deleteAll();

            // --- CREATE STUDENTS ---
            for (int i = 1; i <= 5; i++) {
                Student s = new Student();
                s.setCode("S00" + i);
                s.setName(faker.name().fullName());
                s.setEmail(faker.internet().emailAddress());
                s.setYear(rand.nextInt(3) + 1);
                studentRepo.save(s);
            }

            List<Student> students = studentRepo.findAll();

            // --- CREATE INSTRUCTORS ---
            Instructor i1 = new Instructor();
            i1.setName(faker.name().fullName());
            i1.setEmail(faker.internet().emailAddress());
            instructorRepo.save(i1);

            Instructor i2 = new Instructor();
            i2.setName(faker.name().fullName());
            i2.setEmail(faker.internet().emailAddress());
            instructorRepo.save(i2);

            Instructor i3 = new Instructor();
            i3.setName(faker.name().fullName());
            i3.setEmail(faker.internet().emailAddress());
            instructorRepo.save(i3);

            // --- CREATE PACKS ---
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

            // --- CREATE COMPULSORY COURSES ---
            Course compulsory1 = new Course();
            compulsory1.setType("compulsory");
            compulsory1.setCode("CS201");
            compulsory1.setAbbr("ALG");
            compulsory1.setName("Algorithms");
            compulsory1.setGroupCount(2);
            compulsory1.setDescription("Algorithms and data structures");
            compulsory1.setInstructor(i1);
            compulsory1.setPack(p1);
            courseRepo.save(compulsory1);

            Course compulsory2 = new Course();
            compulsory2.setType("compulsory");
            compulsory2.setCode("CS301");
            compulsory2.setAbbr("DBS");
            compulsory2.setName("Databases");
            compulsory2.setGroupCount(1);
            compulsory2.setDescription("Introduction to SQL and databases");
            compulsory2.setInstructor(i2);
            compulsory2.setPack(p2);
            courseRepo.save(compulsory2);

            // --- CREATE OPTIONAL COURSES ---
            Course optional1 = new Course();
            optional1.setType("optional");
            optional1.setCode("CS401");
            optional1.setAbbr("ML");
            optional1.setName("Machine Learning");
            optional1.setGroupCount(1);
            optional1.setDescription("Introduction to Machine Learning");
            optional1.setInstructor(i3);
            optional1.setPack(p1);
            courseRepo.save(optional1);

            Course optional2 = new Course();
            optional2.setType("optional");
            optional2.setCode("CS402");
            optional2.setAbbr("AI");
            optional2.setName("Artificial Intelligence");
            optional2.setGroupCount(1);
            optional2.setDescription("AI fundamentals");
            optional2.setInstructor(i3);
            optional2.setPack(p1);
            courseRepo.save(optional2);

            // --- CREATE GRADES --- folosind studentCode și courseCode
            for (Student student : students) {
                // Grade for Algorithms (folosind courseCode "CS201")
                Grade grade1 = new Grade();
                grade1.setStudentCode(student.getCode()); // "S001", "S002", etc.
                grade1.setCourseCode("CS201"); // Codul cursului Algorithms
                grade1.setGrade(rand.nextInt(5) + 6); // Note între 6-10
                gradeRepo.save(grade1);

                // Grade for Databases (folosind courseCode "CS301")
                Grade grade2 = new Grade();
                grade2.setStudentCode(student.getCode());
                grade2.setCourseCode("CS301"); // Codul cursului Databases
                grade2.setGrade(rand.nextInt(5) + 6); // Note între 6-10
                gradeRepo.save(grade2);
            }

            System.out.println(" CREATE: Cursurile și notele au fost adăugate în baza de date.");

            // --- READ ---
            System.out.println("\n READ: Cursurile existente în DB:");
            List<Course> courses = courseRepo.findAll();
            courses.forEach(c ->
                    System.out.println(" - " + c.getName() + " (" + c.getCode() + ") - " + c.getType())
            );

            // Afișează notele
            System.out.println("\n READ: Notele studenților:");
            List<Grade> grades = gradeRepo.findAll();
            grades.forEach(g ->
                    System.out.println(" - " + g.getStudentCode() + " la " +
                            g.getCourseCode() + ": " + g.getGrade())
            );

            // Afișează cursurile optionale cu ID-urile lor (important pentru curl commands)
            System.out.println("\n CURSURI OPTIONALE (pentru preferințe instructor):");
            courses.stream()
                    .filter(c -> "optional".equals(c.getType()))
                    .forEach(c -> System.out.println(" - ID: " + c.getId() + " - " + c.getCode() + " (" + c.getAbbr() + ")"));

            System.out.println("\n CURSURI COMPULSORY (pentru note):");
            courses.stream()
                    .filter(c -> "compulsory".equals(c.getType()))
                    .forEach(c -> System.out.println(" - " + c.getCode() + " (" + c.getAbbr() + ")"));

            System.out.println("\n Toate operațiunile CRUD au fost finalizate cu succes!");
        };
    }
}