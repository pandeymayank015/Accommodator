package com.project.Accommodator.config.student;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * An implementation of the Spring Security `AuthenticationManager` interface that does not perform any authentication logic.
 * <p>
 * The `authenticate` method always returns null, which can be useful for testing or temporarily disabling authentication
 * for certain endpoints or requests.
 */
public class StudentAuthenticationManager implements AuthenticationManager {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }


}
