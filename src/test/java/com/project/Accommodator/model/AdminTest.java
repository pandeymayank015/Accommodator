package com.project.Accommodator.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdminTest {
    private Admin admin;

    @BeforeEach
    public void setUp() {
        admin = new Admin();
    }

    @Test
    public void testEmailGetterAndSetter() {
        String email = "admin@example.com";
        admin.setEmail(email);
        assertEquals(email, admin.getEmail(), "Email should be set and retrieved correctly.");
    }

    @Test
    public void testPasswordGetterAndSetter() {
        String password = "password123";
        admin.setPassword(password);
        assertEquals(password, admin.getPassword(), "Password should be set and retrieved correctly.");
    }

    @Test
    public void testConstructor() {
        String email = "admin@example.com";
        String password = "password123";
        Admin adminWithConstructor = new Admin(email, password);
        assertEquals(email, adminWithConstructor.getEmail(), "Email should be set correctly using the constructor.");
        assertEquals(password, adminWithConstructor.getPassword(), "Password should be set correctly using the constructor.");
    }
}
