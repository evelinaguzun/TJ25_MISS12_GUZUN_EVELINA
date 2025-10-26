package com.example.lab4.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // compulsory sau optional
    private String code;
    private String abbr;
    private String name;
    private int groupCount;
    private String description;

    // relații
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @ManyToOne
    @JoinColumn(name = "pack_id")
    private Pack pack;

    // --- Getteri și Setteri ---
    public Long getId() { return id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getAbbr() { return abbr; }
    public void setAbbr(String abbr) { this.abbr = abbr; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getGroupCount() { return groupCount; }
    public void setGroupCount(int groupCount) { this.groupCount = groupCount; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Instructor getInstructor() { return instructor; }
    public void setInstructor(Instructor instructor) { this.instructor = instructor; }

    public Pack getPack() { return pack; }
    public void setPack(Pack pack) { this.pack = pack; }
}
