package com.project.Accommodator.authentication;


import com.project.Accommodator.auth.owner.*;
import com.project.Accommodator.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerAuthenticationControllerTest {

    @InjectMocks
    private OwnerAuthenticationController ownerAuthenticationController;

    @Mock
    private OwnerAuthenticationService ownerAuthenticationService;

    private Owner owner;
    private OwnerAuthenticationRequest ownerAuthenticationRequest;
    private OwnerDto ownerDto;
    private OwnerAuthenticationResponse ownerAuthenticationResponse;

    @BeforeEach
    void setUp() {
        owner = new Owner();
        owner.setOwnerId(1);
        owner.setEmail("owner@example.com");
        owner.setFirstName("John");
        owner.setLastName("Doe");
        owner.setContactNo("1234567890");
        owner.setOwnerType("Individual");
        owner.setPassword("password");

        ownerAuthenticationRequest = new OwnerAuthenticationRequest();
        ownerAuthenticationRequest.setEmail("owner@example.com");
        ownerAuthenticationRequest.setPassword("password");

        ownerDto = new OwnerDto(1, "owner@example.com", "John", "Doe", "1234567890", "Individual");

        ownerAuthenticationResponse = new OwnerAuthenticationResponse("mock-token", ownerDto);
    }

    @Test
    void register() {
        when(ownerAuthenticationService.register(any(Owner.class))).thenReturn(ownerAuthenticationResponse);

        ResponseEntity<OwnerAuthenticationResponse> responseEntity = ownerAuthenticationController.register(owner);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(ownerAuthenticationResponse, responseEntity.getBody());
    }

    @Test
    void authenticate() {
        when(ownerAuthenticationService.authenticate(any(OwnerAuthenticationRequest.class))).thenReturn(ownerAuthenticationResponse);

        ResponseEntity<OwnerAuthenticationResponse> responseEntity = ownerAuthenticationController.authenticate(ownerAuthenticationRequest);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(ownerAuthenticationResponse, responseEntity.getBody());
    }
}

