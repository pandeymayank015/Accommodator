package com.project.Accommodator.service;

import com.project.Accommodator.model.OwnerPreferences;
import com.project.Accommodator.model.Student;

import java.util.List;

/**

 The OwnerPreferencesService interface defines the methods that can be performed by the service
 layer for OwnerPreferences.
 */
public interface OwnerPreferencesService {
    public OwnerPreferences createOwnerPreferences(OwnerPreferences studentPreferences);
    public List<Student> matchOwnerPreferences(String university, String foodPreference, String isSmoking, String isDrinking, String livingSpace, String studyEnvironment, String nationality);
}
