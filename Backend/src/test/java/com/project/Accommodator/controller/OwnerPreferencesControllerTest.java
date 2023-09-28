package com.project.Accommodator.controller;
import com.project.Accommodator.model.OwnerPreferences;
import com.project.Accommodator.model.Student;
import com.project.Accommodator.service.OwnerPreferencesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class OwnerPreferencesControllerTest {
    @Autowired
    private OwnerPreferencesController ownerPreferencesControllerMock;
    OwnerPreferencesService ownerPreferencesServiceMock;
    @BeforeEach
    public void setup(){
        ownerPreferencesServiceMock = mock(OwnerPreferencesService.class);
        ownerPreferencesControllerMock.ownerPreferencesService = ownerPreferencesServiceMock;
    }
    @Test
    public void objectCreated() throws Exception {
        assertNotNull(ownerPreferencesControllerMock);
    }

    @Test
    public void testCreateOwnerPreferences() throws Exception {
        OwnerPreferences expectedOwnerPreferences = new OwnerPreferences(1);
        when(ownerPreferencesServiceMock.createOwnerPreferences(expectedOwnerPreferences)).thenReturn(expectedOwnerPreferences);
        OwnerPreferences actualOwnerPreferences = ownerPreferencesControllerMock.createOwnerPreferences(expectedOwnerPreferences);
        assertEquals(expectedOwnerPreferences, actualOwnerPreferences);
    }
    @Test
    public void testMatchOwnerPreferences() throws Exception {
        String university = "Test University";
        String foodPreference = "Test Food Preference";
        String isSmoking = "Test Smoking";
        String isDrinking = "Test Drinking";
        String livingSpace = "Test Living Space";
        String studyEnvironment = "Test Study Environment";
        String nationality = "Test Nationality";
        List<Student> expectedStudents = new ArrayList<>();
        expectedStudents.add(new Student());

        when(ownerPreferencesServiceMock.matchOwnerPreferences(university, foodPreference, isSmoking, isDrinking, livingSpace, studyEnvironment, nationality))
                .thenReturn(expectedStudents);

        List<Student> actualStudents = ownerPreferencesControllerMock.matchOwnerPreferences(university, foodPreference, isSmoking, isDrinking, livingSpace, studyEnvironment, nationality);

        assertEquals(expectedStudents, actualStudents);
    }
}