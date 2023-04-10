package com.project.Accommodator.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FavoriteTest {
    private Favorite favorite;

    @BeforeEach
    public void setUp() {
        favorite = new Favorite();
    }

    @Test
    public void testFavoriteIdGetterAndSetter() {
        int favoriteId = 1;
        favorite.setFavoriteId(favoriteId);
        assertEquals(favoriteId, favorite.getFavoriteId(), "FavoriteId should be set and retrieved correctly.");
    }

    @Test
    public void testStudentIdGetterAndSetter() {
        int studentId = 2;
        favorite.setStudentId(studentId);
        assertEquals(studentId, favorite.getStudentId(), "StudentId should be set and retrieved correctly.");
    }

    @Test
    public void testPostIdGetterAndSetter() {
        int postId = 3;
        favorite.setPostId(postId);
        assertEquals(postId, favorite.getPostId(), "PostId should be set and retrieved correctly.");
    }

    @Test
    public void testConstructor() {
        int favoriteId = 1;
        int studentId = 2;
        int postId = 3;
        Favorite favoriteWithConstructor = new Favorite(favoriteId, studentId, postId);
        assertEquals(favoriteId, favoriteWithConstructor.getFavoriteId(), "FavoriteId should be set correctly using the constructor.");
        assertEquals(studentId, favoriteWithConstructor.getStudentId(), "StudentId should be set correctly using the constructor.");
        assertEquals(postId, favoriteWithConstructor.getPostId(), "PostId should be set correctly using the constructor.");
    }
}
