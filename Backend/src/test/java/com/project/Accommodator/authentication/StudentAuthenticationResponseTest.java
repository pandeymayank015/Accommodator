package com.project.Accommodator.authentication;


import com.project.Accommodator.auth.student.StudentAuthenticationResponse;
import com.project.Accommodator.auth.student.StudentDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StudentAuthenticationResponseTest {

    @Test
    public void testStudentAuthenticationResponse() {
        String token = "jwtToken";
        StudentDto studentDto = new StudentDto(1, "John", "Doe", "johndoe@example.com", 1234567890L);

        StudentAuthenticationResponse response = StudentAuthenticationResponse.builder()
                .token(token)
                .student(studentDto)
                .build();

        assertNotNull(response);
        assertEquals(token, response.getToken());
        assertEquals(studentDto, response.getStudent());
    }
}
