package com.project.Accommodator.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseTest {
    private Response response;

    @BeforeEach
    public void setUp() {
        response = new Response("success", "Operation successful");
    }


    @Test
    public void testStatusGetterAndSetter() {
        String status = "success";
        response.setStatus(status);
        assertEquals(status, response.getStatus(), "Status should be set and retrieved correctly.");
    }

    @Test
    public void testMessageGetterAndSetter() {
        String message = "Operation successful";
        response.setMessage(message);
        assertEquals(message, response.getMessage(), "Message should be set and retrieved correctly.");
    }

    @Test
    public void testConstructor() {
        String status = "success";
        String message = "Operation successful";
        Response responseWithConstructor = new Response(status, message);
        assertEquals(status, responseWithConstructor.getStatus(), "Status should be set correctly using the constructor.");
        assertEquals(message, responseWithConstructor.getMessage(), "Message should be set correctly using the constructor.");
    }
}
