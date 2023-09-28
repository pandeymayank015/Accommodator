package com.project.Accommodator.service.implementation;

import com.project.Accommodator.model.Posting;
import com.project.Accommodator.model.StudentPreferences;
import com.project.Accommodator.repository.StudentPreferencesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class StudentPreferencesServiceImplementationTest {
    @Mock
    private StudentPreferencesRepository studentPreferencesRepositoryMock;

    @InjectMocks
    private StudentPreferencesServiceImplementation studentPreferencesServiceImplementationMock;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateStudentPreferences() {
        StudentPreferences expectedStudentPreferences = new StudentPreferences();
        when(studentPreferencesRepositoryMock.save(expectedStudentPreferences)).thenReturn(expectedStudentPreferences);
        StudentPreferences actualStudentPreferences = studentPreferencesServiceImplementationMock.createStudentPreferences(expectedStudentPreferences);
        assertEquals(expectedStudentPreferences, actualStudentPreferences);
    }
    @Test
    public void testMatchStudentPreferences(){
        String university = "Test University";
        String foodPreference = "Test Food Preference";
        String isSmoking = "Test Smoking";
        String isDrinking = "Test Drinking";
        String livingSpace = "Test Living Space";
        String studyEnvironment = "Test Study Environment";
        String nationality = "Test Nationality";
        List<Posting> expectedPostings = new ArrayList<>();
        expectedPostings.add(new Posting(1));
        expectedPostings.add(new Posting(2));
        when(studentPreferencesRepositoryMock.matchStudentPreferences(university,foodPreference,isSmoking,isDrinking,livingSpace,studyEnvironment,nationality)).thenReturn(expectedPostings);
        List<Posting> actualPostings = studentPreferencesServiceImplementationMock.matchStudentPreferences(university,foodPreference,isSmoking,isDrinking,livingSpace,studyEnvironment,nationality);
        assertEquals(expectedPostings,actualPostings);
    }
}