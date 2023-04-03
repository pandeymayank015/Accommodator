package com.project.Accommodator.service;
import com.project.Accommodator.model.Posting;
import com.project.Accommodator.model.Student;

public interface PostingService {
    Posting createPosting(Posting Posting);

    Iterable<Posting> getPostingById(int id);

    Iterable<Posting> getAllPosts();

}
