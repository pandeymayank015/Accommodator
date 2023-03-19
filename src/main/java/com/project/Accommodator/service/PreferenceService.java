package com.project.Accommodator.service;
import com.project.Accommodator.model.Preference;

public interface PreferenceService {
    Preference createPreference(Preference Preference);

    Preference getPreferenceById(int id);

}
