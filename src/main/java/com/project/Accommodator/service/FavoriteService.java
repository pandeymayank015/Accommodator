package com.project.Accommodator.service;
import com.project.Accommodator.model.Favorite;
import com.project.Accommodator.model.Posting;

public interface FavoriteService {
    Favorite createFavorite(Favorite Favorite);

    Iterable<Posting> getFavoriteById(int id);

}
