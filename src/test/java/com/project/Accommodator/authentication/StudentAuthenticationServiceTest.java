package com.project.Accommodator.authentication;

import com.project.Accommodator.auth.student.StudentAuthenticationResponse;
import com.project.Accommodator.auth.student.StudentAuthenticationService;
import com.project.Accommodator.config.student.StudentJwtService;
import com.project.Accommodator.model.Student;
import com.project.Accommodator.repository.StudentRepository;
import com.project.Accommodator.token.student.StudentToken;
import com.project.Accommodator.token.student.StudentTokenRepository;
import com.project.Accommodator.token.student.TokenType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentAuthenticationServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentTokenRepository studentTokenRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private StudentJwtService jwtService;

    @Mock
    @Qualifier("studentAuthenticationManager")
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private StudentAuthenticationService studentAuthenticationService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @InjectMocks
    private StudentAuthenticationService studentAuthService;

    @Captor
    private ArgumentCaptor<StudentToken> tokenCaptor;

    @Test
    public void testSaveUserToken() {
        Student student = new Student();
        student.setStudentId(1);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("john.doe@example.com");
        student.setContactNo(1234567890L);

        String jwtToken = "abc123";

        studentAuthService.saveUserToken(student, jwtToken);

        verify(studentTokenRepository).save(tokenCaptor.capture());

        StudentToken capturedToken = tokenCaptor.getValue();

        assertNotNull(capturedToken);
        assertEquals(student, capturedToken.getUser());
        assertEquals(jwtToken, capturedToken.getToken());
        assertEquals(TokenType.BEARER, capturedToken.getTokenType());
        assertFalse(capturedToken.isExpired());
        assertFalse(capturedToken.isRevoked());
    }

    @Test
    void testRegister() throws Exception {
        MultipartHttpServletRequest request = mock(MultipartHttpServletRequest.class);
        when(request.getParameter("firstName")).thenReturn("John");
        when(request.getParameter("lastName")).thenReturn("Doe");
        when(request.getParameter("email")).thenReturn("johndoe@example.com");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("contactNo")).thenReturn("1234567890");
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "offerLetter", "test.txt", "text/plain", "test".getBytes());
        when(request.getFile("offerLetter")).thenReturn(mockMultipartFile);
        Student student = Student.builder()
                .studentId(1)
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@example.com")
                .contactNo(1234567890L)
                .offerLetter(mockMultipartFile.getBytes())
                .password("password")
                .build();
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(jwtService.generateToken(any(Student.class))).thenReturn("jwtToken");
        StudentAuthenticationResponse response = studentAuthenticationService.register(request);
        assertNotNull(response);
        assertEquals("jwtToken", response.getToken());
        assertEquals(student.getStudentId(), response.getStudent().getId());
        assertEquals(student.getFirstName(), response.getStudent().getFirstName());
        assertEquals(student.getLastName(), response.getStudent().getLastName());
        assertEquals(student.getEmail(), response.getStudent().getEmail());
        assertEquals(student.getContactNo(), response.getStudent().getContactNo());
    }


}