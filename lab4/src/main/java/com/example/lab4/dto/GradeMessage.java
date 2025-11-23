package com.example.lab4.dto;

public class GradeMessage {
    private String studentCode;
    private String courseCode;
    private int grade;

    public GradeMessage() {}

    public String getStudentCode() { return studentCode; }
    public void setStudentCode(String studentCode) { this.studentCode = studentCode; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public int getGrade() { return grade; }
    public void setGrade(int grade) { this.grade = grade; }
}
