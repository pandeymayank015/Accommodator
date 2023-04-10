package com.project.Accommodator.authentication;



import com.project.Accommodator.model.PostRequestBody;
import com.project.Accommodator.model.Posting;
import com.project.Accommodator.repository.PostingRepository;
import com.project.Accommodator.service.implementation.PostingServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PostingServiceImplementationTest {

    private PostingServiceImplementation postingService;

    @Mock
    private PostingRepository postingRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        postingService = new PostingServiceImplementation();
        postingService.postingRepository = postingRepository;
    }

    @Test
    void createPostingWithImage() throws IOException {
        PostRequestBody postRequestBody = new PostRequestBody();
        postRequestBody.setAddress("Test Address");
        postRequestBody.setCategory("Test Category");
        postRequestBody.setDate("2023-04-10");
        postRequestBody.setDescription("Test Description");
        postRequestBody.setRent("1000");
        postRequestBody.setType("Test Type");
        postRequestBody.setPincode("123456");
        postRequestBody.setEmail("test@example.com");
        postRequestBody.setOwnerId(1);

        MultipartFile image = mock(MultipartFile.class);
        byte[] imageBytes = new byte[]{1, 2, 3};
        when(image.isEmpty()).thenReturn(false);
        when(image.getBytes()).thenReturn(imageBytes);
        postRequestBody.setImage(image);

        Posting savedPost = new Posting();
        savedPost.setPostId(1);
        when(postingRepository.save(any(Posting.class))).thenReturn(savedPost);

        Posting result = (Posting) postingService.createPosting(postRequestBody);
        assertEquals(1, result.getPostId());
    }

    @Test
    void createPostingWithoutImage() {
        PostRequestBody postRequestBody = new PostRequestBody();
        // Set other properties as in the previous test case

        Posting savedPost = new Posting();
        savedPost.setPostId(2);
        when(postingRepository.save(any(Posting.class))).thenReturn(savedPost);

        Posting result = (Posting) postingService.createPosting(postRequestBody);
        assertEquals(2, result.getPostId());
    }

    @Test
    void getPostingById() {
        int id = 1;
        Posting post = new Posting();
        post.setPostId(id);
        when(postingRepository.getPostingById(id)).thenReturn(Arrays.asList(post));

        Iterable<Posting> result = postingService.getPostingById(id);
        List<Posting> resultList = (List<Posting>) result;

        assertEquals(1, resultList.size());
        assertEquals(id, resultList.get(0).getPostId());
    }

    @Test
    void getAllPosts() {
        Posting post1 = new Posting();
        post1.setPostId(1);

        Posting post2 = new Posting();
        post2.setPostId(2);

        when(postingRepository.findAll()).thenReturn(Arrays.asList(post1, post2));

        Iterable<Posting> result = postingService.getAllPosts();
        List<Posting> resultList = (List<Posting>) result;

        assertEquals(2, resultList.size());
    }
}
