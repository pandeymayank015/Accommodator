package com.project.Accommodator.controller;
import com.project.Accommodator.model.Posting;
import com.project.Accommodator.model.StudentPreferences;
import com.project.Accommodator.model.Student;
import com.project.Accommodator.service.StudentPreferencesService;
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
class StudentPreferencesControllerTest {
    @Autowired
    private StudentPreferencesController studentPreferencesControllerMock;
    StudentPreferencesService studentPreferencesServiceMock;
    @BeforeEach
    public void setup(){
        studentPreferencesServiceMock = mock(StudentPreferencesService.class);
        studentPreferencesControllerMock.studentPreferencesService = studentPreferencesServiceMock;
    }
    @Test
    public void objectCreated() throws Exception {
        assertNotNull(studentPreferencesControllerMock);
    }

    @Test
    public void testCreateStudentPreferences() throws Exception {
        StudentPreferences expectedStudentPreferences = new StudentPreferences(1);
        when(studentPreferencesServiceMock.createStudentPreferences(expectedStudentPreferences)).thenReturn(expectedStudentPreferences);
        StudentPreferences actualStudentPreferences = studentPreferencesControllerMock.createStudentPreferences(expectedStudentPreferences);
        assertEquals(expectedStudentPreferences, actualStudentPreferences);
    }
    @Test
    public void testMatchStudentPreferences() throws Exception {
        String university = "Test University";
        String foodPreference = "Test Food Preference";
        String isSmoking = "Test Smoking";
        String isDrinking = "Test Drinking";
        String livingSpace = "Test Living Space";
        String studyEnvironment = "Test Study Environment";
        String nationality = "Test Nationality";
        List<Posting> expectedPostings = new ArrayList<>();
        expectedPostings.add(new Posting());

        when(studentPreferencesServiceMock.matchStudentPreferences(university, foodPreference, isSmoking, isDrinking, livingSpace, studyEnvironment, nationality))
                .thenReturn(expectedPostings);

        List<Posting> actualStudents = studentPreferencesControllerMock.matchStudentPreferences(university, foodPreference, isSmoking, isDrinking, livingSpace, studyEnvironment, nationality);

        assertEquals(expectedPostings, actualStudents);
    }
}