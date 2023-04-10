package com.project.Accommodator.authentication;

import com.project.Accommodator.auth.student.*;
import com.project.Accommodator.model.Student;
import com.project.Accommodator.service.StudentService;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentAuthenticationControllerTest {

    @InjectMocks
    private StudentAuthenticationController studentAuthenticationController;

    @Mock
    private StudentAuthenticationService studentAuthenticationService;

    @Mock
    private MultipartHttpServletRequest mockRequest;

    private Student student1;
    private Student student2;

    @Mock
    private StudentService studentService;
    private Student student;
    private StudentAuthenticationRequest studentAuthenticationRequest;
    private StudentDto studentDto;
    private StudentAuthenticationResponse studentAuthenticationResponse;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setStudentId(1);
        student.setEmail("student@example.com");
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setContactNo(1234567890L);
        student.setPassword("password");

        studentAuthenticationRequest = new StudentAuthenticationRequest();
        studentAuthenticationRequest.setEmail("student@example.com");
        studentAuthenticationRequest.setPassword("password");

        studentDto = new StudentDto(1,  "John", "Doe","student@example.com", 1234567890L);

        studentAuthenticationResponse = new StudentAuthenticationResponse("mock-token", studentDto);

        student1 = new Student();
        student1.setStudentId(1);
        student1.setFirstName("John");
        student1.setLastName("Doe");
        student1.setEmail("john@example.com");

        student2 = new Student();
        student2.setStudentId(2);
        student2.setFirstName("Jane");
        student2.setLastName("Doe");
        student2.setEmail("jane@example.com");
    }

    @Test
    void register() throws IOException {
        when(studentAuthenticationService.register(any(MultipartHttpServletRequest.class))).thenReturn(studentAuthenticationResponse);

        ResponseEntity<StudentAuthenticationResponse> responseEntity = studentAuthenticationController.register(mockRequest);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(studentAuthenticationResponse, responseEntity.getBody());
    }

    @Test
    void authenticate() {
        when(studentAuthenticationService.authenticate(any(StudentAuthenticationRequest.class))).thenReturn(studentAuthenticationResponse);

        ResponseEntity<StudentAuthenticationResponse> responseEntity = studentAuthenticationController.authenticate(studentAuthenticationRequest);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(studentAuthenticationResponse, responseEntity.getBody());
    }

//    @Test
//    void getStudentById() {
//        when(studentService.getStudentById(anyInt())).thenReturn(student1);
//
//        Student result = studentAuthenticationController.getStudentById(1);
//
//        assertEquals(student1, result);
//    }

//    @Test
//    void getAllStudents() {
//        List<Student> students = Arrays.asList(student1, student2);
//        when(studentService.getAllStudents()).thenReturn(students);
//
//        Iterable<Student> result = studentAuthenticationController.getAllStudents();
//
//        Assertions.assertIterableEquals(students, result);
//    }
}
//package com.project.Accommodator.authentication;
//import com.project.Accommodator.auth.student.StudentAuthenticationController;
//import com.project.Accommodator.model.Student;
//import com.project.Accommodator.service.StudentService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class StudentAuthenticationControllerTest {
//
//    @InjectMocks
//    private StudentAuthenticationController studentAuthenticationController;
//
//
//    @Mock
//    private StudentService studentService;
//
//    private Student student1;
//    private Student student2;
//
//    @BeforeEach
//    void setUp() {
//        student1 = new Student();
//        student1.setStudentId(1);
//        student1.setFirstName("John");
//        student1.setLastName("Doe");
//        student1.setEmail("john@example.com");
//
//        student2 = new Student();
//        student2.setStudentId(2);
//        student2.setFirstName("Jane");
//        student2.setLastName("Doe");
//        student2.setEmail("jane@example.com");
//    }
//
//    @Test
//    void getStudentById() {
//        when(studentService.getStudentById(anyInt())).thenReturn(student1);
//
//        Student result = studentAuthenticationController.getStudentById(1);
//
//        assertEquals(student1, result);
//    }
//
//    @Test
//    void getAllStudents() {
//        List<Student> students = Arrays.asList(student1, student2);
//        when(studentService.getAllStudents()).thenReturn(students);
//
//        Iterable<Student> result = studentAuthenticationController.getAllStudents();
//
//        assertEquals(students, result);
//    }
//}
