package com.project.Accommodator.controller;
import com.project.Accommodator.model.Preference;
import com.project.Accommodator.service.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/preference")
public class PreferenceController {
    @Autowired
    PreferenceService PreferenceService;
    @CrossOrigin

    @GetMapping("/get/{id}")
    public Preference getPreferenceById(@PathVariable("id") int id) {
        return PreferenceService.getPreferenceById(id);
    }
    @CrossOrigin
    @PostMapping("/create")
    public Preference createPreference(@RequestBody Preference Preference) {
        return PreferenceService.createPreference(Preference);
    }
}
