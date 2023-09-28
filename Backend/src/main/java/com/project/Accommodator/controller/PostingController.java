package com.project.Accommodator.controller;
import com.project.Accommodator.model.PostRequestBody;
import com.project.Accommodator.model.Posting;
import com.project.Accommodator.service.PostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * A REST controller class that handles HTTP requests related to Posting resources.
 */
@CrossOrigin
@RestController
@RequestMapping("/posting")

public class    PostingController {

    /**
     * The Posting service instance that will be used to process requests.
     */
    @Autowired
    PostingService PostingService;
    @CrossOrigin

    /**
     * Retrieves a Posting with the specified ID.
     *
     * @param id the ID of the Posting to retrieve.
     * @return the Posting with the specified ID.
     */
    @GetMapping("/get/{id}")
    public Iterable<Posting> getPostingById(@PathVariable("id") int id) {
        return PostingService.getPostingById(id);
    }

    /**
     * Creates a new Posting.
     *
     * @param Posting the Posting to create.
     * @return the created Posting.
     */
    @CrossOrigin
    @PostMapping("/create")
    public Object createPosting(@ModelAttribute PostRequestBody Posting) {
        return PostingService.createPosting(Posting);
    }
    /**
     * Retrieves all Postings.
     *
     * @return all Postings.
     */
    @CrossOrigin
    @GetMapping("/get/all")
    public Iterable<Posting> getAllPosts() {
        return PostingService.getAllPosts();
    }
}
