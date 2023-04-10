package com.project.Accommodator.authentication;

import com.project.Accommodator.*;

import com.project.Accommodator.config.student.StudentJwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class StudentJwtServiceTest {
    private StudentJwtService studentJwtService;
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        studentJwtService = new StudentJwtService();
        userDetails = User.withUsername("testUser")
                .password("testPassword")
                .roles("USER")
                .build();
    }

    @Test
    void extractUsername() {
        String token = studentJwtService.generateToken(userDetails);
        String extractedUsername = studentJwtService.extractUsername(token);
        assertEquals(userDetails.getUsername(), extractedUsername);
    }


    @Test
    void generateToken() {
        String token = studentJwtService.generateToken(userDetails);
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void isTokenValid() {
        String token = studentJwtService.generateToken(userDetails);
        assertTrue(studentJwtService.isTokenValid(token, userDetails));
    }

    @Test
    void isTokenInvalidForDifferentUser() {
        UserDetails anotherUser = User.withUsername("anotherUser")
                .password("anotherPassword")
                .roles("USER")
                .build();
        String token = studentJwtService.generateToken(userDetails);
        assertFalse(studentJwtService.isTokenValid(token, anotherUser));
    }

    @Test
    void isTokenExpired() throws InterruptedException {
        UserDetails shortLivedUser = User.withUsername("shortLivedUser")
                .password("shortLivedPassword")
                .roles("USER")
                .build();
        String token = studentJwtService.generateToken(shortLivedUser);
        Thread.sleep(1000 * 1 ); // Sleep for a little over 24 minutes
        assertFalse(studentJwtService.isTokenExpired(token));
    }
}

