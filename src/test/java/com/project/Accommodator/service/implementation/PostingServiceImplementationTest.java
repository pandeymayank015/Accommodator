package com.project.Accommodator.service.implementation;

import com.project.Accommodator.model.PostRequestBody;
import com.project.Accommodator.model.Posting;
import com.project.Accommodator.repository.PostingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class PostingServiceImplementationTest {
    @Mock
    private PostingRepository postingRepositoryMock;

    @InjectMocks
    private PostingServiceImplementation postingServiceImplementationMock;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
//    @Test
//    public void testCreatePosting() {
//        PostRequestBody postRequestBody = new PostRequestBody();
//        Posting expectedPosting = new Posting();
//        postRequestBody.setCategory("Lease");
//        when(postingRepositoryMock.save(expectedPosting)).thenReturn(expectedPosting);
//        Object actualPosting = postingServiceImplementationMock.createPosting(postRequestBody);
//        assertEquals(expectedPosting, actualPosting);
//    }

    @Test
    public void testGetPostingById() {
        int id = 1;
        Iterable<Posting> expectedPostings = postingRepositoryMock.findAll();
        when(postingRepositoryMock.getPostingById(id)).thenReturn((List<Posting>) expectedPostings);
        Iterable<Posting> actualPostings = postingServiceImplementationMock.getPostingById(id);
        assertEquals(expectedPostings, actualPostings);
    }
    @Test
    public void testGetAllPosts() {
        Iterable<Posting> expectedPostings = postingRepositoryMock.findAll();
        when(postingRepositoryMock.findAll()).thenReturn(expectedPostings);
        Iterable<Posting> actualPostings = postingServiceImplementationMock.getAllPosts();
        assertEquals(expectedPostings, actualPostings);
    }
}