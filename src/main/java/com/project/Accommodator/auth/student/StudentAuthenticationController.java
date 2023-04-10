package com.project.Accommodator.auth.student;

import com.project.Accommodator.config.student.StudentJwtService;
import com.project.Accommodator.model.Student;
import com.project.Accommodator.repository.StudentRepository;
import com.project.Accommodator.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
@CrossOrigin
public class StudentAuthenticationController {


  @Autowired
  StudentService studentService;

  private final PasswordEncoder passwordEncoder;
  private final StudentJwtService jwtService;
  private final StudentAuthenticationService service;
  private final StudentRepository repository;


  /**
   * Handles HTTP POST requests to register a new student.
   *
   * @param request a multipart HTTP request containing student information including first name, last name, email,
   *                password, contact number, and offer letter
   * @return a HTTP response containing a {@code StudentAuthenticationResponse} object
   * @throws IOException if an input/output error occurs
   */
  @PostMapping("/create")
  public ResponseEntity<StudentAuthenticationResponse> register(MultipartHttpServletRequest request) throws IOException{

    return ResponseEntity.ok(service.register(request));
  }


  /**
   * Handles HTTP POST requests to authenticate a student.
   *
   * @param request a HTTP request containing student authentication information including email and password
   * @return a HTTP response containing a {@code StudentAuthenticationResponse} object
   */
  @PostMapping("/login")
  public ResponseEntity<StudentAuthenticationResponse> authenticate(
      @RequestBody StudentAuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @GetMapping("/get/{id}")
    public Student getStudentById(@PathVariable("id") int id) {
        return studentService.getStudentById(id);
    }
  @CrossOrigin
  @GetMapping("/get/all")
  public Iterable<Student> getAllStudents() {
    return studentService.getAllStudents();
  }


}
