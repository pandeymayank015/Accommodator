package com.project.Accommodator.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostingTest {
    private Posting posting;

    @BeforeEach
    public void setUp() {
        posting = new Posting();
    }

    @Test
    public void testPostId() {
        int postId = 1;
        posting.setPostId(postId);
        assertEquals(postId, posting.getPostId());
    }

    @Test
    public void testRent() {
        String rent = "1000";
        posting.setRent(rent);
        assertEquals(rent, posting.getRent());
    }

    @Test
    public void testAddress() {
        String address = "123 Test Street";
        posting.setAddress(address);
        assertEquals(address, posting.getAddress());
    }

    @Test
    public void testDescription() {
        String description = "Test description";
        posting.setDescription(description);
        assertEquals(description, posting.getDescription());
    }



    @Test
    public void testCategory() {
        String category = "Test Category";
        posting.setCategory(category);
        assertEquals(category, posting.getCategory());
    }

    @Test
    public void testImage() {
        byte[] image = new byte[] {1, 2, 3};
        posting.setImage(image);
        assertEquals(image, posting.getImage());
    }


    @Test
    public void testPincode() {
        String pincode = "12345";
        posting.setPincode(pincode);
        assertEquals(pincode, posting.getPincode());
    }

    @Test
    public void testEmail() {
        String email = "test@example.com";
        posting.setEmail(email);
        assertEquals(email, posting.getEmail());
    }

    @Test
    public void testOwnerId() {
        int ownerId = 1;
        posting.setOwnerId(ownerId);
        assertEquals(ownerId, posting.getOwnerId());
    }
}

//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.Arrays;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class PostingTest {
//    private Posting posting;
//
//    @BeforeEach
//    public void setUp() {
//        posting = new Posting();
//    }
//
//    @Test
//    public void testPostIdGetterAndSetter() {
//        Integer postId = 1;
//        posting.setPostId(postId);
//        assertEquals(postId, posting.getPostId(), "PostId should be set and retrieved correctly.");
//    }
//
//    @Test
//    public void testImageGetterAndSetter() {
//        byte[] image = new byte[] {1, 2, 3};
//        posting.setImage(image);
//        assertTrue(Arrays.equals(image, posting.getImage()), "Image should be set and retrieved correctly.");
//    }
//
//    @Test
//    public void testConstructor() {
//        Integer postId = 1;
//        String rent = "1000";
//        String address = "123 Main St";
//        String description = "A nice place";
//        String date = "2023-04-09";
//        String category = "Apartment";
//        byte[] image = new byte[] {1, 2, 3};
//        String type = "Rent";
//        String pincode = "12345";
//        String email = "test@example.com";
//        Integer ownerId = 1;
//
//        Posting postingWithConstructor = new Posting(postId, rent, address, description, date, category, image, type, pincode, email, ownerId);
//        assertEquals(postId, postingWithConstructor.getPostId(), "PostId should be set correctly using the constructor.");
//
//        assertTrue(Arrays.equals(image, postingWithConstructor.getImage()), "Image should be set correctly using the constructor.");
//    }
//}
