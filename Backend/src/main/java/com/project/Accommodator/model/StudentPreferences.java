package com.project.Accommodator.model;
import jakarta.persistence.*;
@Entity
@Table(name = "StudentPreferences")
public class StudentPreferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentPreferencesId;
    private String university;
    private String nationality;
    private String food;
    private String smokingPref;
    private String drinkingPref;
    private String livingPref;
    private String studyPref;
    private String interests;
    private int studentId;

    public StudentPreferences() {
    }

    public StudentPreferences(int id) {
        this.studentPreferencesId = id;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getSmokingPref() {
        return smokingPref;
    }

    public void setSmokingPref(String smokingPref) {
        this.smokingPref = smokingPref;
    }

    public String getDrinkingPref() {
        return drinkingPref;
    }

    public void setDrinkingPref(String drinkingPref) {
        this.drinkingPref = drinkingPref;
    }

    public String getLivingPref() {
        return livingPref;
    }

    public void setLivingPref(String livingPref) {
        this.livingPref = livingPref;
    }

    public String getStudyPref() {
        return studyPref;
    }

    public void setStudyPref(String studyPref) {
        this.studyPref = studyPref;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
