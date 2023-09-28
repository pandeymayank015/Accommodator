package com.project.Accommodator.authentication;

import static org.junit.jupiter.api.Assertions.*;

import com.project.Accommodator.auth.owner.OwnerAuthenticationRequest;
import org.junit.jupiter.api.Test;

public class OwnerAuthenticationRequestTest {

    @Test
    void createOwnerAuthenticationRequest_withAllFields_Success() {
        OwnerAuthenticationRequest request = OwnerAuthenticationRequest.builder()
                .email("test@gmail.com")
                .password("testpassword")
                .build();
        assertNotNull(request);
        assertEquals("test@gmail.com", request.getEmail());
        assertEquals("testpassword", request.getPassword());
    }

    @Test
    void createOwnerAuthenticationRequest_withNoFields_Success() {
        OwnerAuthenticationRequest request = new OwnerAuthenticationRequest();
        assertNotNull(request);
        assertNull(request.getEmail());
        assertNull(request.getPassword());
    }

    @Test
    void setEmail_and_getEmail_Success() {
        OwnerAuthenticationRequest request = new OwnerAuthenticationRequest();
        request.setEmail("test@gmail.com");
        assertNotNull(request.getEmail());
        assertEquals("test@gmail.com", request.getEmail());
    }

    @Test
    void setPassword_and_getPassword_Success() {
        OwnerAuthenticationRequest request = new OwnerAuthenticationRequest();
        request.setPassword("testpassword");
        assertNotNull(request.getPassword());
        assertEquals("testpassword", request.getPassword());
    }
}
