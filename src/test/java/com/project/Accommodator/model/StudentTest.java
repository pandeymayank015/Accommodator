package com.project.Accommodator.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    private Student student;

    @BeforeEach
    public void setUp() {
        student = new Student();
        student.setStudentId(1);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setOfferLetter(new byte[0]);
        student.setEmail("john.doe@example.com");
        student.setPassword("password");
        student.setIsApproved(1);
        student.setContactNo(1234567890L);
    }

    @Test
    public void testGetters() {
        assertEquals(1, student.getStudentId());
        assertEquals("John", student.getFirstName());
        assertEquals("Doe", student.getLastName());
        assertArrayEquals(new byte[0], student.getOfferLetter());
        assertEquals("john.doe@example.com", student.getEmail());
        assertEquals("password", student.getPassword());
        assertEquals(1, student.getIsApproved());
        assertEquals(1234567890L, student.getContactNo());
    }

    @Test
    public void testUserDetailsMethods() {
        assertTrue(student.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_STUDENT")));
        assertEquals("john.doe@example.com", student.getUsername());
        assertTrue(student.isAccountNonExpired());
        assertTrue(student.isAccountNonLocked());
        assertTrue(student.isCredentialsNonExpired());
        assertTrue(student.isEnabled());
    }
}
