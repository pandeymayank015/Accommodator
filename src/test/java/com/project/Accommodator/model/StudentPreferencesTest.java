package com.project.Accommodator.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentPreferencesTest {
    private StudentPreferences studentPreferences;

    @BeforeEach
    public void setUp() {
        studentPreferences = new StudentPreferences();
        studentPreferences.setUniversity("University A");
        studentPreferences.setNationality("American");
        studentPreferences.setFood("Vegetarian");
        studentPreferences.setSmokingPref("Non-smoker");
        studentPreferences.setDrinkingPref("Non-drinker");
        studentPreferences.setLivingPref("Private room");
        studentPreferences.setStudyPref("Quiet");
        studentPreferences.setInterests("Sports");
        studentPreferences.setStudentId(1);
    }

    @Test
    public void testGetters() {

        assertEquals("University A", studentPreferences.getUniversity());
        assertEquals("American", studentPreferences.getNationality());
        assertEquals("Vegetarian", studentPreferences.getFood());
        assertEquals("Non-smoker", studentPreferences.getSmokingPref());
        assertEquals("Non-drinker", studentPreferences.getDrinkingPref());
        assertEquals("Private room", studentPreferences.getLivingPref());
        assertEquals("Quiet", studentPreferences.getStudyPref());
        assertEquals("Sports", studentPreferences.getInterests());
        assertEquals(1, studentPreferences.getStudentId());
    }
}
