package com.project.Accommodator.authentication;

import com.project.Accommodator.*;

import com.project.Accommodator.config.student.StudentLogoutService;
import com.project.Accommodator.token.student.StudentToken;
import com.project.Accommodator.token.student.StudentTokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class StudentLogoutServiceTest {

    private StudentLogoutService studentLogoutService;

    @Mock
    private StudentTokenRepository tokenRepository;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        studentLogoutService = new StudentLogoutService(tokenRepository);
    }

    @Test
    void logoutWithValidToken() {
        String jwt = "validToken";
        StudentToken storedToken = new StudentToken();
        storedToken.setToken(jwt);
        storedToken.setExpired(false);
        storedToken.setRevoked(false);

        when(request.getHeader("Authorization")).thenReturn("Bearer " + jwt);
        when(tokenRepository.findByToken(jwt)).thenReturn(Optional.of(storedToken));

        studentLogoutService.logout(request, response, authentication);

        assertTrue(storedToken.isExpired());
        assertTrue(storedToken.isRevoked());
        verify(tokenRepository).save(storedToken);
    }

    @Test
    void logoutWithInvalidToken() {
        String jwt = "invalidToken";

        when(request.getHeader("Authorization")).thenReturn("Bearer " + jwt);
        when(tokenRepository.findByToken(jwt)).thenReturn(Optional.empty());

        studentLogoutService.logout(request, response, authentication);

        verify(tokenRepository, never()).save(any(StudentToken.class));
    }

    @Test
    void logoutWithoutToken() {
        when(request.getHeader("Authorization")).thenReturn(null);

        studentLogoutService.logout(request, response, authentication);

        verify(tokenRepository, never()).findByToken(anyString());
        verify(tokenRepository, never()).save(any(StudentToken.class));
    }

    @Test
    void logoutWithMalformedAuthHeader() {
        when(request.getHeader("Authorization")).thenReturn("Invalid Auth Header");

        studentLogoutService.logout(request, response, authentication);

        verify(tokenRepository, never()).findByToken(anyString());
        verify(tokenRepository, never()).save(any(StudentToken.class));
    }
}
