package com.project.Accommodator.config.owner;

import com.project.Accommodator.token.owner.OwnerTokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

/**

 Service class for handling owner logout functionality. Implements {@link org.springframework.security.web.authentication.logout.LogoutHandler}.
 This service invalidates the JWT token of the owner user after logout by updating the corresponding record in the token repository.
 */
@Service
@RequiredArgsConstructor
public class OwnerLogoutService implements LogoutHandler {

  private final OwnerTokenRepository tokenRepository;

  /**

   Method that handles logout of owner user by invalidating the JWT token.
   Extracts the JWT token from the Authorization header of the request and updates the corresponding record in the token repository.
   @param request HTTP servlet request
   @param response HTTP servlet response
   @param authentication Authentication object representing the owner user's authentication information
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
