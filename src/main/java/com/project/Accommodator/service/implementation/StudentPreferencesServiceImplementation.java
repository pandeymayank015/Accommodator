package com.project.Accommodator.service.implementation;

import com.project.Accommodator.model.Posting;
import com.project.Accommodator.model.Student;
import com.project.Accommodator.model.StudentPreferences;
import com.project.Accommodator.repository.StudentPreferencesRepository;
import com.project.Accommodator.service.StudentPreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentPreferencesServiceImplementation implements StudentPreferencesService {
    @Autowired
    StudentPreferencesRepository studentPreferencesRepository;

    public StudentPreferencesServiceImplementation() {
        super();
    }

    @Override
    public StudentPreferences createStudentPreferences(StudentPreferences studentPreferences) {
        return studentPreferencesRepository.save(studentPreferences);
    }

    @Override
    public List<Posting> matchStudentPreferences(String university, String foodPreference, String isSmoking, String isDrinking, String livingSpace, String studyEnvironment, String nationality) {
        return studentPreferencesRepository.matchStudentPreferences(university, foodPreference, isSmoking, isDrinking, livingSpace, studyEnvironment, nationality);
    }
}
