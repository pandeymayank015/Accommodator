package com.project.Accommodator.controller;

import com.project.Accommodator.model.OwnerPreferences;
import com.project.Accommodator.model.Student;
import com.project.Accommodator.model.StudentPreferences;
import com.project.Accommodator.service.OwnerPreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/ownerpref")
public class OwnerPreferencesController {

    @Autowired
    OwnerPreferencesService ownerPreferencesService;
    @CrossOrigin
    @PostMapping("/create")
    public OwnerPreferences createOwnerPreferences(@RequestBody OwnerPreferences ownerPreferences) {
        return ownerPreferencesService.createOwnerPreferences(ownerPreferences);
    }
    @CrossOrigin
    @PostMapping("/match")
    public List<Student> matchOwnerPreferences(@RequestParam("university") String university,
                                               @RequestParam("foodPreference") String foodPreference,
                                               @RequestParam("isSmoking") String isSmoking,
                                               @RequestParam("isDrinking") String isDrinking,
                                               @RequestParam("livingSpace") String livingSpace,
                                               @RequestParam("studyEnvironment") String studyEnvironment,
                                               @RequestParam("nationality") String nationality) {
        return ownerPreferencesService.matchOwnerPreferences(university, foodPreference, isSmoking, isDrinking, livingSpace, studyEnvironment, nationality);
    }
}
