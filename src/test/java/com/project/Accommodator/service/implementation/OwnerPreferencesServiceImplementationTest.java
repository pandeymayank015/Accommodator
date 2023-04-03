package com.project.Accommodator.service.implementation;
import com.project.Accommodator.model.OwnerPreferences;
import com.project.Accommodator.model.Student;
import com.project.Accommodator.repository.OwnerPreferencesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class OwnerPreferencesServiceImplementationTest {
    @Mock
    private OwnerPreferencesRepository ownerPreferencesRepositoryMock;

    @InjectMocks
    private OwnerPreferencesServiceImplementation ownerPreferencesServiceImplementationMock;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOwnerPreferences() {
        OwnerPreferences expectedOwnerPreferences = new OwnerPreferences();
        when(ownerPreferencesRepositoryMock.save(expectedOwnerPreferences)).thenReturn(expectedOwnerPreferences);
        OwnerPreferences actualOwnerPreferences = ownerPreferencesServiceImplementationMock.createOwnerPreferences(expectedOwnerPreferences);
        assertEquals(expectedOwnerPreferences, actualOwnerPreferences);
    }
    @Test
    public void testMatchOwnerPreferences(){
        String university = "Test University";
        String foodPreference = "Test Food Preference";
        String isSmoking = "Test Smoking";
        String isDrinking = "Test Drinking";
        String livingSpace = "Test Living Space";
        String studyEnvironment = "Test Study Environment";
        String nationality = "Test Nationality";
        List<Student> expectedStudents = new ArrayList<>();
        expectedStudents.add(new Student());
        expectedStudents.add(new Student());
        when(ownerPreferencesRepositoryMock.matchOwnerPreferences(university,foodPreference,isSmoking,isDrinking,livingSpace,studyEnvironment,nationality)).thenReturn(expectedStudents);
        List<Student> actualStudents = ownerPreferencesServiceImplementationMock.matchOwnerPreferences(university,foodPreference,isSmoking,isDrinking,livingSpace,studyEnvironment,nationality);
        assertEquals(expectedStudents,actualStudents);
    }
}