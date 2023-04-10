package com.project.Accommodator.config.owner;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**

 Custom implementation of Spring Security's {@link AuthenticationManager} for owner authentication.
 This class is responsible for validating the owner's credentials and returning an {@link Authentication} object if the authentication is successful.
 */
public class OwnerAuthenticationManager implements AuthenticationManager {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        return null;
    }


}
