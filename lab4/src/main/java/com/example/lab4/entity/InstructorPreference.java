package com.example.lab4.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "instructor_preferences")
public class InstructorPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "compulsory_course_abbr")
    private String compulsoryCourseAbbr;

    private Integer percentage;

    public InstructorPreference() {}

    public InstructorPreference(Course course, String compulsoryCourseAbbr, Integer percentage) {
        this.course = course;
        this.compulsoryCourseAbbr = compulsoryCourseAbbr;
        this.percentage = percentage;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public String getCompulsoryCourseAbbr() { return compulsoryCourseAbbr; }
    public void setCompulsoryCourseAbbr(String compulsoryCourseAbbr) { this.compulsoryCourseAbbr = compulsoryCourseAbbr; }

    public Integer getPercentage() { return percentage; }
    public void setPercentage(Integer percentage) { this.percentage = percentage; }
}
