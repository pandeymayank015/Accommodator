package com.project.Accommodator.controller;
import com.project.Accommodator.model.Favorite;
import com.project.Accommodator.model.Posting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**

 This class represents the REST API endpoints for favorites.
 */
@CrossOrigin
@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    /**

     Retrieves the list of postings favorited by the given user ID.
     @param id the ID of the user whose favorites should be retrieved
     @return an Iterable of Posting objects representing the user's favorites
     */
    @Autowired
    com.project.Accommodator.service.FavoriteService FavoriteService;
    @CrossOrigin
    @GetMapping("/get/{id}")
    public Iterable<Posting> getFavoriteById(@PathVariable("id") int id) {
        return FavoriteService.getFavoriteById(id);
    }
    /**

     Creates a new favorite for the given user.
     @param
     @return the created Favorite object
     */
    @CrossOrigin
    @PostMapping("/create")
    public Favorite createFavorite(@RequestBody Favorite Favorite) {
        return FavoriteService.createFavorite(Favorite);
    }
}
//package com.project.Accommodator.controller;
//import com.project.Accommodator.model.Favorite;
//import com.project.Accommodator.model.Posting;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.http.ResponseEntity;

//@CrossOrigin
//@RestController
//@RequestMapping("/favorite")
//public class FavoriteController {
//    @Autowired
//    com.project.Accommodator.service.FavoriteService FavoriteService;
//
//    @CrossOrigin
//    @GetMapping("/get/{id}")
//    public Iterable<Posting> getFavoriteById(@PathVariable("id") int id) {
//        return FavoriteService.getFavoriteById(id);
//    }
//
////    @CrossOrigin
////    @PostMapping("/create")
////    public Favorite createFavorite(@RequestParam("studentId") int studentId, @RequestParam("postId") int postId) {
////        return FavoriteService.createFavorite(studentId, postId);
////    }
//// @CrossOrigin
//// @PostMapping("/create")
//// public ResponseEntity<Favorite> createFavorite(@RequestParam("studentId") int studentId, @RequestParam("postId") int postId) {
////    Favorite favorite = FavoriteService.createFavorite(studentId, postId);
////    return ResponseEntity.ok(favorite);
////}
//
//}
