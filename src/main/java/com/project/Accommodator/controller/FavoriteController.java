package com.project.Accommodator.controller;
import com.project.Accommodator.model.Favorite;
import com.project.Accommodator.model.Posting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/favorite")
public class FavoriteController {
    @Autowired
    com.project.Accommodator.service.FavoriteService FavoriteService;
    @CrossOrigin
    @GetMapping("/get/{id}")
    public Iterable<Posting> getFavoriteById(@PathVariable("id") int id) {
        return FavoriteService.getFavoriteById(id);
    }
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
