package com.project.Accommodator.repository;

import com.project.Accommodator.model.OwnerPreferences;
import com.project.Accommodator.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnerPreferencesRepository extends CrudRepository<OwnerPreferences, Integer> {
    @Query("SELECT s FROM StudentPreferences o INNER JOIN Student s ON s.studentId = o.studentId\n" +
            "WHERE CASE WHEN o.university = ?1 THEN 1 ELSE 0 END +\n" +
            "      CASE WHEN o.food = ?2 THEN 1 ELSE 0 END +\n" +
            "      CASE WHEN o.smokingPref = ?3 THEN 1 ELSE 0 END +\n" +
            "      CASE WHEN o.drinkingPref = ?4 THEN 1 ELSE 0 END +\n" +
            "      CASE WHEN o.livingPref = ?5 THEN 1 ELSE 0 END +\n" +
            "      CASE WHEN o.studyPref = ?6 THEN 1 ELSE 0 END +\n" +
            "      CASE WHEN o.nationality = ?7 THEN 1 ELSE 0 END > 2\n")
    List<Student> matchOwnerPreferences(String university, String foodPreference, String isSmoking, String isDrinking, String livingSpace, String studyEnvironment, String nationality);
}
