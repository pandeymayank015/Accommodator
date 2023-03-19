package com.project.Accommodator.auth.student;

import com.project.Accommodator.config.student.StudentJwtService;
import com.project.Accommodator.model.Student;
import com.project.Accommodator.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
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


  private final PasswordEncoder passwordEncoder;
  private final StudentJwtService jwtService;
  private final StudentAuthenticationService service;
  private final StudentRepository repository;

//  @PostMapping("/create")
//  public ResponseEntity<StudentAuthenticationResponse> register(
//      @RequestBody Student request
//  ) {
//    System.out.println("hello");
//    return ResponseEntity.ok(service.register(request));
//  }

  @PostMapping("/create")
  public ResponseEntity<StudentAuthenticationResponse> register(MultipartHttpServletRequest request) throws IOException{

    return ResponseEntity.ok(service.register(request));
  }
//@PostMapping("/create")
//public ResponseEntity<StudentAuthenticationResponse> register(MultipartHttpServletRequest request) throws IOException {
//  var firstName = request.getParameter("firstName");
//  var lastName = request.getParameter("lastName");
//  var email = request.getParameter("email");
//  var password = request.getParameter("password");
//  var contactNo = Long.parseLong(request.getParameter("contactNo"));
//  var offerLetter = request.getFile("offerLetter");
//
//  var user = Student.builder()
//          .firstName(firstName)
//          .lastName(lastName)
//          .email(email)
//          .contactNo(contactNo)
//          .offerLetter(offerLetter.getBytes())
//          .password(passwordEncoder.encode(password))
//          .build();
//  var savedUser = repository.save(user);
//  var jwtToken = jwtService.generateToken(user);
//  saveUserToken(savedUser, jwtToken);
//  return ResponseEntity.ok(StudentAuthenticationResponse.builder()
//          .token(jwtToken)
//          .build());
//}


  @PostMapping("/login")
  public ResponseEntity<StudentAuthenticationResponse> authenticate(
      @RequestBody StudentAuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }


}
