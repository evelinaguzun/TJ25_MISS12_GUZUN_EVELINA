package com.example.lab4.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "packs")
public class Pack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int year;
    private int semester;
    private String name;

    // --- Getteri și Setteri ---
    public Long getId() { return id; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public int getSemester() { return semester; }
    public void setSemester(int semester) { this.semester = semester; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
