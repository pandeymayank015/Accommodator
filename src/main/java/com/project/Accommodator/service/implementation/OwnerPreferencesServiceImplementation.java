package com.project.Accommodator.service.implementation;
import com.project.Accommodator.model.OwnerPreferences;
import com.project.Accommodator.model.Student;
import com.project.Accommodator.repository.OwnerPreferencesRepository;
import com.project.Accommodator.service.OwnerPreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerPreferencesServiceImplementation implements OwnerPreferencesService {
    @Autowired
    OwnerPreferencesRepository ownerPreferencesRepository;

    public OwnerPreferencesServiceImplementation() {
        super();
    }

    @Override
    public OwnerPreferences createOwnerPreferences(OwnerPreferences ownerPreferences) {
        return ownerPreferencesRepository.save(ownerPreferences);
    }
    @Override
    public List<Student> matchOwnerPreferences(String university, String foodPreference, String isSmoking, String isDrinking, String livingSpace, String studyEnvironment, String nationality){
        return ownerPreferencesRepository.matchOwnerPreferences(university, foodPreference, isSmoking, isDrinking, livingSpace, studyEnvironment, nationality);
    }
}
