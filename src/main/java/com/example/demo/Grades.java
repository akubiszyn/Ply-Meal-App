package com.example.demo;
public class Grades {
    private int grade_id;
    private int grade;
    private String description;

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(int grade_id) {
        this.grade_id = grade_id;
    }

    @Override
    public String toString() {
        return "Grades{" +
                "grade_id=" + grade_id +
                ", grade=" + grade +
                ", description='" + description + '\'' +
                '}';
    }
}