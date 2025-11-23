package com.example.lab4.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "student_preferences")
public class StudentPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    @JsonIgnoreProperties("preferences")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    @JsonIgnoreProperties("preferences")
    private Course course;

    @NotNull
    private int preferenceOrder; // 1 = cel mai preferat, 2, 3...

    // --- Getteri și Setteri ---
    public Long getId() { return id; }
    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public int getPreferenceOrder() { return preferenceOrder; }

    public void setStudent(Student student) { this.student = student; }
    public void setCourse(Course course) { this.course = course; }
    public void setPreferenceOrder(int preferenceOrder) { this.preferenceOrder = preferenceOrder; }
}
