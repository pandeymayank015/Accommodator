package com.project.Accommodator.repository;

import com.project.Accommodator.model.Favorite;
import com.project.Accommodator.model.Posting;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FavoriteRepository extends CrudRepository<Favorite, Integer> {
    @Query("SELECT p FROM Favorite f INNER JOIN Posting p ON p.postId = f.postId WHERE f.studentId = ?1")
    List<Posting> findFavoritesById(int id);
}
//package com.project.Accommodator.repository;
//
//import com.project.Accommodator.model.Favorite;
//import com.project.Accommodator.model.Posting;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//
//import java.util.List;
//
//public interface FavoriteRepository extends CrudRepository<Favorite, Integer> {
//    @Query("SELECT f.posting FROM Favorite f WHERE f.student.studentId = ?1")
//    List<Posting> findFavoritesById(int id);
//}
