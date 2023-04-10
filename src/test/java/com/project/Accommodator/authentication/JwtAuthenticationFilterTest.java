package com.project.Accommodator.authentication;


import com.project.Accommodator.config.JwtAuthenticationFilter;
import com.project.Accommodator.config.owner.OwnerJwtService;
import com.project.Accommodator.config.student.StudentJwtService;
import com.project.Accommodator.token.owner.OwnerTokenRepository;
import com.project.Accommodator.token.student.StudentToken;
import com.project.Accommodator.token.student.StudentTokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;
import java.util.Optional;

import static org.mockito.Mockito.*;

class JwtAuthenticationFilterTest {

    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Mock
    private StudentJwtService studentJwtService;

    @Mock
    private OwnerJwtService ownerJwtService;

    @Mock
    private UserDetailsService studentUserDetailsService;

    @Mock
    private UserDetailsService ownerUserDetailsService;

    @Mock
    private StudentTokenRepository studentTokenRepository;

    @Mock
    private OwnerTokenRepository ownerTokenRepository;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private UserDetails userDetails;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtAuthenticationFilter = new JwtAuthenticationFilter(studentJwtService, ownerJwtService, studentTokenRepository, ownerTokenRepository);
        jwtAuthenticationFilter.studentUserDetailsService = studentUserDetailsService;
        jwtAuthenticationFilter.ownerUserDetailsService = ownerUserDetailsService;
    }


    @Test
    void doFilterInternalValidStudentToken() throws IOException, ServletException {
        String jwt = "validStudentToken";
        String userEmail = "fn@dal.ca";

        when(request.getHeader("Authorization")).thenReturn("Bearer " + jwt);
        when(studentJwtService.extractUsername(jwt)).thenReturn(userEmail);
        when(studentTokenRepository.findByToken(jwt)).thenReturn(Optional.of(new StudentToken()));
        when(studentUserDetailsService.loadUserByUsername(userEmail)).thenReturn(userDetails);
        when(studentJwtService.isTokenValid(jwt, userDetails)).thenReturn(true);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
    }

    @Test
    void doFilterInternalValidOwnerToken() throws IOException, ServletException {
        String jwt = "validOwnerToken";
        String userEmail = "owner@example.com";

        when(request.getHeader("Authorization")).thenReturn("Bearer " + jwt);
        when(studentJwtService.extractUsername(jwt)).thenReturn(userEmail);
        when(studentTokenRepository.findByToken(jwt)).thenReturn(Optional.empty());
        when(ownerUserDetailsService.loadUserByUsername(userEmail)).thenReturn(userDetails);
        when(ownerJwtService.isTokenValid(jwt, userDetails)).thenReturn(true);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
    }

    @Test
    void doFilterInternalInvalidToken() throws IOException, ServletException {
        String jwt = "invalidToken";
        String userEmail = "invalid@example.com";

        when(request.getHeader("Authorization")).thenReturn("Bearer " + jwt);
        when(studentJwtService.extractUsername(jwt)).thenReturn(userEmail);
        when(studentTokenRepository.findByToken(jwt)).thenReturn(Optional.empty());
        when(ownerUserDetailsService.loadUserByUsername(userEmail)).thenReturn(userDetails);
        when(ownerJwtService.isTokenValid(jwt, userDetails)).thenReturn(false);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
    }

    @Test
    void doFilterInternalNoAuthHeader() throws IOException, ServletException {
        when(request.getHeader("Authorization")).thenReturn(null);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        verify(studentJwtService, never()).extractUsername(anyString());
    }

    @Test
    void doFilterInternalMalformedAuthHeader() throws IOException, ServletException {
        when(request.getHeader("Authorization")).thenReturn("Invalid Auth Header");

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        verify(studentJwtService, never()).extractUsername(anyString());
    }
}

