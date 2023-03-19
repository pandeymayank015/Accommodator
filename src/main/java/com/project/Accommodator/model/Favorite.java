package com.project.Accommodator.model;

import jakarta.persistence.*;

@Entity
@Table(name="Favorite")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int favoriteId;
    private int studentId;
    private int postId;

    public Favorite() {
    }

    public Favorite(int favoriteId, int studentId, int postId) {
        this.favoriteId = favoriteId;
        this.studentId = studentId;
        this.postId = postId;
    }

    public int getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(int favoriteId) {
        this.favoriteId = favoriteId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
