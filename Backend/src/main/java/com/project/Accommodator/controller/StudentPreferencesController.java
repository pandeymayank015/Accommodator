package com.project.Accommodator.controller;

import com.project.Accommodator.model.Posting;
import com.project.Accommodator.model.Student;
import com.project.Accommodator.model.StudentPreferences;
import com.project.Accommodator.service.StudentPreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**

 This controller handles HTTP requests related to Student Preferences.
 It is responsible for creating and matching student preferences with postings.
 */
@CrossOrigin
@RestController
@RequestMapping("/studentpref")
public class StudentPreferencesController {

        @Autowired
        StudentPreferencesService studentPreferencesService;

        /**
         * POST endpoint for creating a new Student Preferences object.
         * @param studentPreferences The Student Preferences object to be created.
         * @return The newly created Student Preferences object.
         */
        @CrossOrigin
        @PostMapping("/create")
        public StudentPreferences createStudentPreferences(@RequestBody StudentPreferences studentPreferences) {
            return studentPreferencesService.createStudentPreferences(studentPreferences);
        }

        /**
         * POST endpoint for matching student preferences with postings.
         * @param university The name of the university.
         * @param foodPreference The food preference of the student.
         * @param isSmoking The smoking preference of the student.
         * @param isDrinking The drinking preference of the student.
         * @param livingSpace The preferred living space of the student.
         * @param studyEnvironment The preferred study environment of the student.
         * @param nationality The nationality of the student.
         * @return A list of matching postings.
         */
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
