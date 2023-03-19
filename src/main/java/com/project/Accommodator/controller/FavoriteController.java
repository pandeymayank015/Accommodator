package com.project.Accommodator.controller;
import com.project.Accommodator.model.Favorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {
    @Autowired
    com.project.Accommodator.service.FavoriteService FavoriteService;
    @CrossOrigin

    @GetMapping("/get/{id}")
    public Favorite getFavoriteById(@PathVariable("id") int id) {
        return FavoriteService.getFavoriteById(id);
    }
    @CrossOrigin
    @PostMapping("/create")
    public Favorite createFavorite(@RequestBody Favorite Favorite) {
        return FavoriteService.createFavorite(Favorite);
    }
}
