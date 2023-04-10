package com.project.Accommodator.authentication;



import com.project.Accommodator.auth.owner.OwnerAuthenticationResponse;
import com.project.Accommodator.auth.owner.OwnerAuthenticationService;
import com.project.Accommodator.config.owner.OwnerJwtService;
import com.project.Accommodator.model.Owner;
import com.project.Accommodator.repository.OwnerRepository;
import com.project.Accommodator.token.owner.OwnerTokenRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerAuthenticationServiceTest {

    @InjectMocks
    private OwnerAuthenticationService ownerAuthenticationService;

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private OwnerTokenRepository ownerTokenRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private OwnerJwtService ownerJwtService;

    @Test
    void register() {
        // Arrange
        Owner owner = new Owner();
        owner.setEmail("test@example.com");
        owner.setPassword("test123");

        when(ownerRepository.save(any(Owner.class))).thenReturn(owner);
        when(passwordEncoder.encode(anyString())).thenReturn("encoded_password");
        when(ownerJwtService.generateToken(any(Owner.class))).thenReturn("jwt_token");

        // Act
        OwnerAuthenticationResponse response = ownerAuthenticationService.register(owner);

        // Assert
        verify(ownerRepository).save(any(Owner.class));
        verify(passwordEncoder).encode(anyString());
        verify(ownerJwtService).generateToken(any(Owner.class));
        verify(ownerTokenRepository).save(any());

        assert(response.getToken().equals("jwt_token"));
        assert(response.getOwner().getEmail().equals("test@example.com"));
    }

    // Add other test cases as needed
}
