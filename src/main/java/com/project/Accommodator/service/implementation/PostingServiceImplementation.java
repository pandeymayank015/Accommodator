package com.project.Accommodator.service.implementation;

import com.project.Accommodator.model.Posting;
import com.project.Accommodator.service.PostingService;
import com.project.Accommodator.repository.PostingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostingServiceImplementation implements PostingService {
    @Autowired
    PostingRepository PostingRepository;

    public PostingServiceImplementation() {
        super();
    }

    @Override
    public Posting createPosting(Posting Posting) {

        return PostingRepository.save(Posting);
    }

    @Override
    public Iterable<Posting> getPostingById(int id) {
        Iterable<Posting> Posting = PostingRepository.getPostingById(id);
        return Posting;
    }
    @Override
    public Iterable<Posting> getAllPosts() {
        Iterable<Posting> Posting = PostingRepository.findAll();
        return Posting;
    }
}