package com.project.Accommodator.model;

import com.project.Accommodator.model.PostRequestBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostRequestBodyTest {
    private PostRequestBody postRequestBody;

    @BeforeEach
    public void setUp() {
        postRequestBody = new PostRequestBody();
    }

    @Test
    public void testRent() {
        String rent = "1000";
        postRequestBody.setRent(rent);
        assertEquals(rent, postRequestBody.getRent());
    }

    @Test
    public void testAddress() {
        String address = "123 Test Street";
        postRequestBody.setAddress(address);
        assertEquals(address, postRequestBody.getAddress());
    }

    @Test
    public void testDescription() {
        String description = "Test description";
        postRequestBody.setDescription(description);
        assertEquals(description, postRequestBody.getDescription());
    }

    @Test
    public void testDate() {
        String date = "2023-04-09";
        postRequestBody.setDate(date);
        assertEquals(date, postRequestBody.getDate());
    }

    @Test
    public void testCategory() {
        String category = "Test Category";
        postRequestBody.setCategory(category);
        assertEquals(category, postRequestBody.getCategory());
    }

    @Test
    public void testImage() {
        MultipartFile image = new MockMultipartFile("testimage", new byte[]{1, 2, 3});
        postRequestBody.setImage(image);
        assertEquals(image, postRequestBody.getImage());
    }

    @Test
    public void testType() {
        String type = "Test Type";
        postRequestBody.setType(type);
        assertEquals(type, postRequestBody.getType());
    }

    @Test
    public void testPincode() {
        String pincode = "12345";
        postRequestBody.setPincode(pincode);
        assertEquals(pincode, postRequestBody.getPincode());
    }

    @Test
    public void testEmail() {
        String email = "test@example.com";
        postRequestBody.setEmail(email);
        assertEquals(email, postRequestBody.getEmail());
    }

    @Test
    public void testOwnerId() {
        int ownerId = 1;
        postRequestBody.setOwnerId(ownerId);
        assertEquals(ownerId, postRequestBody.getOwnerId());
    }
}

//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.web.multipart.MultipartFile;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.mock;
//
//public class PostRequestBodyTest {
//    private PostRequestBody postRequestBody;
//
//    @BeforeEach
//    public void setUp() {
//        postRequestBody = new PostRequestBody();
//    }
//
//    @Test
//    public void testRentGetterAndSetter() {
//        String rent = "1000";
//        postRequestBody.setRent(rent);
//        assertEquals(rent, postRequestBody.getRent(), "Rent should be set and retrieved correctly.");
//    }
//
//
//    @Test
//    public void testImageGetterAndSetter() {
//        MultipartFile image = mock(MultipartFile.class);
//        postRequestBody.setImage(image);
//        assertEquals(image, postRequestBody.getImage(), "Image should be set and retrieved correctly.");
//    }
//
//    @Test
//    public void testConstructor() {
//        String rent = "1000";
//        String address = "123 Main St";
//        String description = "A nice place";
//        String date = "2023-04-09";
//        String category = "Apartment";
//        MultipartFile image = mock(MultipartFile.class);
//        String type = "Rent";
//        String pincode = "12345";
//        String email = "test@example.com";
//        Integer ownerId = 1;
//
//        PostRequestBody postRequestBodyWithConstructor = new PostRequestBody(rent, address, description, date, category, image, type, pincode, email, ownerId);
//        assertEquals(rent, postRequestBodyWithConstructor.getRent(), "Rent should be set correctly using the constructor.");
//        assertEquals(image, postRequestBodyWithConstructor.getImage(), "Image should be set correctly using the constructor.");
//    }
//}
