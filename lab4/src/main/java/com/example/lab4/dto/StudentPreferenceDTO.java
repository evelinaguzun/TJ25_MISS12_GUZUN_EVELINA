package com.example.lab4.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class StudentPreferenceDTO {
    @NotNull
    private Long studentId;

    @NotNull
    private Long courseId;

    @Min(1)
    private int preferenceOrder;

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }

    public int getPreferenceOrder() { return preferenceOrder; }
    public void setPreferenceOrder(int preferenceOrder) { this.preferenceOrder = preferenceOrder; }
}

