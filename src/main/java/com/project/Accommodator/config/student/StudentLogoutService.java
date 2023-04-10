package com.project.Accommodator.config.student;

import com.project.Accommodator.token.student.StudentTokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

/**
 * Service responsible for logging out a student user by revoking the JWT token and clearing the security context.
 */
@Service
@RequiredArgsConstructor
public class StudentLogoutService implements LogoutHandler {

  private final StudentTokenRepository tokenRepository;

  /**
   * Logs out the student user by revoking the JWT token and clearing the security context.
   *
   * @param request         the HTTP servlet request
   * @param response        the HTTP servlet response
   * @param authentication the authentication object representing the currently authenticated principal
   */
  @Override
  public void logout(
      HttpServletRequest request,
      HttpServletResponse response,
      Authentication authentication
  ) {
    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
    }
    jwt = authHeader.substring(7);
    var storedToken = tokenRepository.findByToken(jwt)
        .orElse(null);
    if (storedToken != null) {
      storedToken.setExpired(true);
      storedToken.setRevoked(true);
      tokenRepository.save(storedToken);
      SecurityContextHolder.clearContext();
    }
  }
}
