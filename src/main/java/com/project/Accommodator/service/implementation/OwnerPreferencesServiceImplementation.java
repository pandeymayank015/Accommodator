package com.project.Accommodator.service.implementation;
import com.project.Accommodator.model.OwnerPreferences;
import com.project.Accommodator.model.Student;
import com.project.Accommodator.repository.OwnerPreferencesRepository;
import com.project.Accommodator.service.OwnerPreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**

 This class is an implementation of the OwnerPreferencesService interface, which provides
 methods for managing owner preferences information in the system.
 */
@Service
public class OwnerPreferencesServiceImplementation implements OwnerPreferencesService {
    @Autowired
    OwnerPreferencesRepository ownerPreferencesRepository;

    /**

     Constructs a new OwnerPreferencesServiceImplementation object.
     */
    public OwnerPreferencesServiceImplementation() {
        super();
    }
    /**

     Saves the given owner preferences information in the system.
     @param ownerPreferences The owner preferences information to be saved.
     @return The saved owner preferences information.
     */
    @Override
    public OwnerPreferences createOwnerPreferences(OwnerPreferences ownerPreferences) {
        return ownerPreferencesRepository.save(ownerPreferences);
    }

    /**

     Finds students who match the given owner preferences criteria.
     @param university The university preference of the students.
     @param foodPreference The food preference of the students.
     @param isSmoking The smoking preference of the students.
     @param isDrinking The drinking preference of the students.
     @param livingSpace The living space preference of the students.
     @param studyEnvironment The study environment preference of the students.
     @param nationality The nationality preference of the students.
     @return A list of students who match the given owner preferences criteria.
     */
    @Override
    public List<Student> matchOwnerPreferences(String university, String foodPreference, String isSmoking, String isDrinking, String livingSpace, String studyEnvironment, String nationality){
        return ownerPreferencesRepository.matchOwnerPreferences(university, foodPreference, isSmoking, isDrinking, livingSpace, studyEnvironment, nationality);
    }
}
