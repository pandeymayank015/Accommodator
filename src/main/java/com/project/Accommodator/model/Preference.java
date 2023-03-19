package com.project.Accommodator.model;

import jakarta.persistence.*;

@Entity
@Table(name="Preferences")
public class Preference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer preferenceId;
    private String food;
    private String language;
    private String university;
    private String course;
    private String hometown;
    private int studentId;
    private int ownerId;

    public Preference() {
    }

    public Preference(Integer preferenceId, String food, String language, String university, String course, String hometown, int studentId, int ownerId) {
        this.preferenceId = preferenceId;
        this.food = food;
        this.language = language;
        this.university = university;
        this.course = course;
        this.hometown = hometown;
        this.studentId = studentId;
        this.ownerId = ownerId;
    }

    public Integer getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(Integer preferenceId) {
        this.preferenceId = preferenceId;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
