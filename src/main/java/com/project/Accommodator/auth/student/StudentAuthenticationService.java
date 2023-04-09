package com.project.Accommodator.auth.student;

import com.project.Accommodator.config.student.StudentJwtService;
import com.project.Accommodator.model.Student;
import com.project.Accommodator.repository.StudentRepository;
import com.project.Accommodator.token.owner.OwnerToken;
import com.project.Accommodator.token.student.StudentToken;
import com.project.Accommodator.token.student.StudentTokenRepository;
import com.project.Accommodator.token.student.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class StudentAuthenticationService {
  private final StudentRepository repository;
  private final StudentTokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final StudentJwtService jwtService;
  @Autowired
  @Qualifier("studentAuthenticationManager")
  private AuthenticationManager authenticationManager;

//  public StudentAuthenticationResponse register(Student request) {
//    var user = Student.builder()
//        .firstName(request.getFirstName())
//        .lastName(request.getLastName())
//        .email(request.getEmail())
//            .contactNo(request.getContactNo())
//            .offerLetter(request.getOfferLetter())
//        .password(passwordEncoder.encode(request.getPassword()))
//        .build();
//    var savedUser = repository.save(user);
//    var jwtToken = jwtService.generateToken(user);
//    saveUserToken(savedUser, jwtToken);
//    return StudentAuthenticationResponse.builder()
//        .token(jwtToken)
//        .build();
//  }

  public StudentAuthenticationResponse register(MultipartHttpServletRequest request) throws IOException {
    var firstName = request.getParameter("firstName");
    var lastName = request.getParameter("lastName");
    var email = request.getParameter("email");
    var password = request.getParameter("password");
    var contactNo = Long.parseLong(request.getParameter("contactNo"));
    var offerLetter = request.getFile("offerLetter");

    var user = Student.builder()
            .firstName(firstName)
            .lastName(lastName)
            .email(email)
            .contactNo(contactNo)
            .offerLetter(offerLetter.getBytes())
            .password(passwordEncoder.encode(password))
            .build();
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    saveUserToken(savedUser, jwtToken);


    StudentDto studentDto = new StudentDto(savedUser.getStudentId(), savedUser.getFirstName(), savedUser.getLastName(), savedUser.getEmail(), savedUser.getContactNo());
    return StudentAuthenticationResponse.builder()
            .token(jwtToken)
            .student(studentDto)
            .build();
  }

  public StudentAuthenticationResponse authenticate(StudentAuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    StudentDto studentDto = new StudentDto(user.getStudentId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getContactNo());
    return StudentAuthenticationResponse.builder()
            .token(jwtToken)
            .student(studentDto)
            .build();
  }

  private void saveUserToken(Student user, String jwtToken) {
    var tokenBuilder = StudentToken.builder();
    var userData=tokenBuilder.user(user);
    var tokenData=userData.token(jwtToken);
    var tokenType=tokenData.tokenType(TokenType.BEARER);
    var expired=tokenType.expired(false);
    var revoked=expired.revoked(false);
    var token=revoked.build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(Student user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getStudentId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }
}
