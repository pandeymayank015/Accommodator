package com.project.Accommodator.service;
import com.project.Accommodator.model.Favorite;

public interface FavoriteService {
    Favorite createFavorite(Favorite Favorite);

    Favorite getFavoriteById(int id);

}
