package com.project.Accommodator.service;
import com.project.Accommodator.model.PostRequestBody;
import com.project.Accommodator.model.Posting;

/**

 The PostingService interface provides methods to create, retrieve and manage postings.
 */
public interface PostingService {
    Object createPosting(PostRequestBody Posting);

    Iterable<Posting> getPostingById(int id);

    Iterable<Posting> getAllPosts();

}
