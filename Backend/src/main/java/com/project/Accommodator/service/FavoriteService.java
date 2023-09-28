package com.project.Accommodator.service;
import com.project.Accommodator.model.Favorite;
import com.project.Accommodator.model.Posting;

/**

 Interface for FavoriteService, responsible for managing operations related to user's favorite postings
 */
public interface FavoriteService {
    Favorite createFavorite(Favorite Favorite);

    Iterable<Posting> getFavoriteById(int id);

}
//package com.project.Accommodator.service;
//import com.project.Accommodator.model.Favorite;
//import com.project.Accommodator.model.Posting;
//
//public interface FavoriteService {
//    Favorite createFavorite(int studentId, int postId);
//    Iterable<Posting> getFavoriteById(int id);
//}
