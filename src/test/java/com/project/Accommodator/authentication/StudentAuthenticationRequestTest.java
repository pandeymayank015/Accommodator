package com.project.Accommodator.authentication;

import com.project.Accommodator.auth.student.StudentAuthenticationRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentAuthenticationRequestTest {

    @Test
    void testConstructorAndGetters() {
        String email = "test@example.com";
        String password = "password123";
        StudentAuthenticationRequest request = new StudentAuthenticationRequest(email, password);
        assertEquals(email, request.getEmail());
        assertEquals(password, request.getPassword());
    }

    @Test
    void testEqualsAndHashCode() {
        String email1 = "test1@example.com";
        String password1 = "password123";
        StudentAuthenticationRequest request1 = new StudentAuthenticationRequest(email1, password1);

        String email2 = "test2@example.com";
        String password2 = "password456";
        StudentAuthenticationRequest request2 = new StudentAuthenticationRequest(email2, password2);

        assertEquals(request1, request1);
        assertNotEquals(request1, null);
        assertNotEquals(request1, "test");

        assertNotEquals(request1, request2);
        assertNotEquals(request1.hashCode(), request2.hashCode());

        StudentAuthenticationRequest request3 = new StudentAuthenticationRequest(email1, password1);
        assertEquals(request1, request3);
        assertEquals(request1.hashCode(), request3.hashCode());
    }

}
