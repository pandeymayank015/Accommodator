package com.project.Accommodator.config.student;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.logout.LogoutHandler;

/**

 Implementation of the Spring Security LogoutHandler interface for student users.
 */
public class StudentLogoutHandler implements LogoutHandler {

    /**
     * Implementation of the logout method from the LogoutHandler interface.
     * This method does not perform any specific action.
     *
     * @param httpServletRequest  the HttpServletRequest object
     * @param httpServletResponse the HttpServletResponse object
     * @param authentication      the current authentication object
     */
    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, org.springframework.security.core.Authentication authentication) {

    }
}
