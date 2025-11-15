package com.example.lab4.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;
    private String email;
    private int year;

    // Relație cu preferințele studentului
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("student")
    private List<StudentPreference> preferences;

    // --- Getteri și Setteri ---
    public Long getId() { return id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public List<StudentPreference> getPreferences() { return preferences; }
    public void setPreferences(List<StudentPreference> preferences) { this.preferences = preferences; }
}

