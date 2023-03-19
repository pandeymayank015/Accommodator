package com.project.Accommodator.service.implementation;

import com.project.Accommodator.model.Favorite;
import com.project.Accommodator.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteServiceImplementation implements FavoriteService {
    @Autowired
    com.project.Accommodator.repository.FavoriteRepository FavoriteRepository;

    public FavoriteServiceImplementation() {
        super();
    }

    @Override
    public Favorite createFavorite(Favorite Favorite) {

        return FavoriteRepository.save(Favorite);
    }

    @Override
    public Favorite getFavoriteById(int id) {
        Favorite Favorite = FavoriteRepository.findById(id).orElse(null);
        return Favorite;
    }
}