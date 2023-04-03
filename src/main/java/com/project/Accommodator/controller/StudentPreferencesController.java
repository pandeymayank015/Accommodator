package com.project.Accommodator.controller;

import com.project.Accommodator.model.Posting;
import com.project.Accommodator.model.Student;
import com.project.Accommodator.model.StudentPreferences;
import com.project.Accommodator.service.StudentPreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/studentpref")
public class StudentPreferencesController {

        @Autowired
        StudentPreferencesService studentPreferencesService;
        @CrossOrigin
        @PostMapping("/create")
        public StudentPreferences createStudentPreferences(@RequestBody StudentPreferences studentPreferences) {
            return studentPreferencesService.createStudentPreferences(studentPreferences);
        }
        @CrossOrigin
        @PostMapping("/match")
        public List<Posting> matchStudentPreferences(@RequestParam("university") String university,
                                                   @RequestParam("foodPreference") String foodPreference,
                                                    @RequestParam("isSmoking") String isSmoking,
                                                   @RequestParam("isDrinking") String isDrinking,
                                                   @RequestParam("livingSpace") String livingSpace,
                                                   @RequestParam("studyEnvironment") String studyEnvironment,
                                                   @RequestParam("nationality") String nationality) {
                return studentPreferencesService.matchStudentPreferences(university, foodPreference, isSmoking, isDrinking, livingSpace, studyEnvironment, nationality);
        }
}
