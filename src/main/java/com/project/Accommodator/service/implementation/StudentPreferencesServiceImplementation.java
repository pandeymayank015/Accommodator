package com.project.Accommodator.service.implementation;

import com.project.Accommodator.model.Posting;
import com.project.Accommodator.model.Student;
import com.project.Accommodator.model.StudentPreferences;
import com.project.Accommodator.repository.StudentPreferencesRepository;
import com.project.Accommodator.service.StudentPreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implements the methods of the StudentPreferencesService interface by using the StudentPreferencesRepository.
 */
@Service
public class StudentPreferencesServiceImplementation implements StudentPreferencesService {
    @Autowired
    StudentPreferencesRepository studentPreferencesRepository;

    public StudentPreferencesServiceImplementation() {
        super();
    }

    /**
     * Saves the provided studentPreferences object to the database using the studentPreferencesRepository.
     *
     * @param studentPreferences the studentPreferences object to be saved.
     * @return the saved studentPreferences object.
     */
    @Override
    public StudentPreferences createStudentPreferences(StudentPreferences studentPreferences) {
        return studentPreferencesRepository.save(studentPreferences);
    }

    /**
     * Retrieves a list of postings that match the provided student preferences criteria from the database
     * using the studentPreferencesRepository.
     *
     * @param university the preferred university of the student.
     * @param foodPreference the preferred food type of the student.
     * @param isSmoking the smoking preference of the student.
     * @param isDrinking the drinking preference of the student.
     * @param livingSpace the preferred living space of the student.
     * @param studyEnvironment the preferred study environment of the student.
     * @param nationality the preferred nationality of the student.
     * @return a list of postings that match the provided student preferences criteria.
     */
    @Override
    public List<Posting> matchStudentPreferences(String university, String foodPreference, String isSmoking, String isDrinking, String livingSpace, String studyEnvironment, String nationality) {
        return studentPreferencesRepository.matchStudentPreferences(university, foodPreference, isSmoking, isDrinking, livingSpace, studyEnvironment, nationality);
    }
}
