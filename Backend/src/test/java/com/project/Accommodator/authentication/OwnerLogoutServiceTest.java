package com.project.Accommodator.authentication;


import com.project.Accommodator.config.owner.OwnerLogoutService;
import com.project.Accommodator.token.owner.OwnerToken;
import com.project.Accommodator.token.owner.OwnerTokenRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class OwnerLogoutServiceTest {

    @Mock
    private OwnerTokenRepository tokenRepository;

    @InjectMocks
    private OwnerLogoutService ownerLogoutService;

    public OwnerLogoutServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLogoutWithNullAuthHeader() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Authentication authentication = mock(Authentication.class);

        when(request.getHeader("Authorization")).thenReturn(null);

        ownerLogoutService.logout(request, response, authentication);

        verify(tokenRepository, never()).findByToken(anyString());
        verify(tokenRepository, never()).save(any());

    }

    @Test
    public void testLogoutWithInvalidAuthHeader() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Authentication authentication = mock(Authentication.class);

        when(request.getHeader("Authorization")).thenReturn("invalid_token");

        ownerLogoutService.logout(request, response, authentication);

        verify(tokenRepository, never()).findByToken(anyString());
        verify(tokenRepository, never()).save(any());

    }

    @Test
    public void testLogoutWithValidAuthHeader() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Authentication authentication = mock(Authentication.class);
        OwnerToken ownerToken = mock(OwnerToken.class);

        String validJwt = "valid_jwt_token";
        when(request.getHeader("Authorization")).thenReturn("Bearer " + validJwt);
        when(tokenRepository.findByToken(validJwt)).thenReturn(Optional.of(ownerToken));

        ownerLogoutService.logout(request, response, authentication);

        verify(ownerToken).setExpired(true);
        verify(ownerToken).setRevoked(true);
        verify(tokenRepository).save(ownerToken);

    }

}
