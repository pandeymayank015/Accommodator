package com.project.Accommodator.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OwnerPreferencesTest {
    private OwnerPreferences ownerPreferences;

    @BeforeEach
    public void setUp() {
        ownerPreferences = new OwnerPreferences();
    }

    @Test
    public void testOwnerPreferencesId() {
        int id = 1;
        ownerPreferences.setOwnerPreferencesId(id);
        assertEquals(id, ownerPreferences.getOwnerPreferencesId());
    }

    @Test
    public void testUniversity() {
        String university = "Test University";
        ownerPreferences.setUniversity(university);
        assertEquals(university, ownerPreferences.getUniversity());
    }

    @Test
    public void testNationality() {
        String nationality = "Test Nationality";
        ownerPreferences.setNationality(nationality);
        assertEquals(nationality, ownerPreferences.getNationality());
    }

    @Test
    public void testFood() {
        String food = "Vegetarian";
        ownerPreferences.setFood(food);
        assertEquals(food, ownerPreferences.getFood());
    }

    @Test
    public void testSmokingPref() {
        String smokingPref = "No";
        ownerPreferences.setSmokingPref(smokingPref);
        assertEquals(smokingPref, ownerPreferences.getSmokingPref());
    }

    @Test
    public void testDrinkingPref() {
        String drinkingPref = "No";
        ownerPreferences.setDrinkingPref(drinkingPref);
        assertEquals(drinkingPref, ownerPreferences.getDrinkingPref());
    }

    @Test
    public void testLivingPref() {
        String livingPref = "Private";
        ownerPreferences.setLivingPref(livingPref);
        assertEquals(livingPref, ownerPreferences.getLivingPref());
    }

    @Test
    public void testStudyPref() {
        String studyPref = "Quiet";
        ownerPreferences.setStudyPref(studyPref);
        assertEquals(studyPref, ownerPreferences.getStudyPref());
    }

    @Test
    public void testOwnerId() {
        int ownerId = 1;
        ownerPreferences.setOwnerId(ownerId);
        assertEquals(ownerId, ownerPreferences.getOwnerId());
    }
}

