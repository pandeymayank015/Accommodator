package com.project.Accommodator.service.implementation;

import com.project.Accommodator.model.Preference;
import com.project.Accommodator.service.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PreferenceServiceImplementation implements PreferenceService {
    @Autowired
    com.project.Accommodator.repository.PreferenceRepository PreferenceRepository;

    public PreferenceServiceImplementation() {
        super();
    }

    @Override
    public Preference createPreference(Preference Preference) {

        return PreferenceRepository.save(Preference);
    }

    @Override
    public Preference getPreferenceById(int id) {
        Preference Preference = PreferenceRepository.findById(id).orElse(null);
        return Preference;
    }
}