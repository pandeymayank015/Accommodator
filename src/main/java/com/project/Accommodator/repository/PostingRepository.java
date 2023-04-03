package com.project.Accommodator.repository;

import com.project.Accommodator.model.Posting;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostingRepository extends CrudRepository<Posting, Integer> {
    @Query("SELECT p FROM Posting p WHERE p.ownerId = ?1")
    List<Posting> getPostingById(int id);
}
