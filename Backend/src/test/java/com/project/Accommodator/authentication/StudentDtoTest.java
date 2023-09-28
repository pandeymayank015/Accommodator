package com.project.Accommodator.authentication;

import com.project.Accommodator.*;
import com.project.Accommodator.auth.student.StudentDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentDtoTest {

    @Test
    public void testConstructorAndGetters() {
        int id = 1;
        String firstName = "John";
        String lastName = "Doe";
        String email = "johndoe@example.com";
        Long contactNo = 1234567890L;

        StudentDto student = new StudentDto(id, firstName, lastName, email, contactNo);

        assertEquals(id, student.getId());
        assertEquals(firstName, student.getFirstName());
        assertEquals(lastName, student.getLastName());
        assertEquals(email, student.getEmail());
        assertEquals(contactNo, student.getContactNo());
    }

    @Test
    public void testSetters() {
        StudentDto student = new StudentDto();
        student.setId(1);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("johndoe@example.com");
        student.setContactNo(1234567890L);

        assertEquals(1, student.getId());
        assertEquals("John", student.getFirstName());
        assertEquals("Doe", student.getLastName());
        assertEquals("johndoe@example.com", student.getEmail());
        assertEquals(1234567890L, student.getContactNo());
    }
}
