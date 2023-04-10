package com.project.Accommodator.controller;

import com.project.Accommodator.model.OwnerPreferences;
import com.project.Accommodator.model.Student;
import com.project.Accommodator.model.StudentPreferences;
import com.project.Accommodator.service.OwnerPreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**

 This controller class handles HTTP requests related to owner preferences.

 It is annotated with @RestController to indicate that all methods in this controller

 return a response in JSON format. It is also annotated with @CrossOrigin to allow

 cross-origin requests from any domain.
 *//**

 This controller class handles HTTP requests related to owner preferences.

 It is annotated with @RestController to indicate that all methods in this controller

 return a response in JSON format. It is also annotated with @CrossOrigin to allow

 cross-origin requests from any domain.
 */
@CrossOrigin
@RestController
@RequestMapping("/ownerpref")
public class OwnerPreferencesController {

    @Autowired
    OwnerPreferencesService ownerPreferencesService;

    /**

     POST endpoint for creating a new owner preferences entry.
     Accepts a request body in JSON format containing the owner preferences data.
     Calls the createOwnerPreferences method of the OwnerPreferencesService to save the data.
     @param ownerPreferences The owner preferences data to be saved.
     @return The saved owner preferences data in JSON format.
     */
    @CrossOrigin
    @PostMapping("/create")
    public OwnerPreferences createOwnerPreferences(@RequestBody OwnerPreferences ownerPreferences) {
        return ownerPreferencesService.createOwnerPreferences(ownerPreferences);
    }

    /**

     POST endpoint for matching owner preferences with students.
     Accepts request parameters containing the owner preferences criteria to match with students.
     Calls the matchOwnerPreferences method of the OwnerPreferencesService to retrieve the matched students.
     @param university The preferred university of the owner.
     @param foodPreference The preferred food type of the owner.
     @param isSmoking The preferred smoking status of the owner.
     @param isDrinking The preferred drinking status of the owner.
     @param livingSpace The preferred living space of the owner.
     @param studyEnvironment The preferred study environment of the owner.
     @param nationality The preferred nationality of the owner.
     @return A list of matched students in JSON format.
     */
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
