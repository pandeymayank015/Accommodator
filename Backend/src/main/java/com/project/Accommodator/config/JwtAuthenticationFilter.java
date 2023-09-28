package com.project.Accommodator.config;

import com.project.Accommodator.config.owner.OwnerJwtService;
import com.project.Accommodator.config.student.StudentJwtService;
import com.project.Accommodator.token.owner.OwnerTokenRepository;
import com.project.Accommodator.token.student.StudentTokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * This filter intercepts incoming requests and checks whether the request contains a valid JWT token in the
 * "Authorization" header. If the token is valid, the filter sets the authentication context of the current request.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final StudentJwtService studentJwtService;
    private final OwnerJwtService ownerJwtService;

    @Autowired
    public UserDetailsService studentUserDetailsService;

    @Autowired
    public UserDetailsService ownerUserDetailsService;

    private final StudentTokenRepository studentTokenRepository;
    private final OwnerTokenRepository ownerTokenRepository;

    /**
     * Filters incoming requests to check for valid JWT tokens in the "Authorization" header. If a valid JWT token
     * is found, the authentication context of the request is set.
     *
     * @param request The incoming HTTP servlet request.
     * @param response The outgoing HTTP servlet response.
     * @param filterChain The filter chain to continue processing the request.
     * @throws ServletException if there is a servlet error.
     * @throws IOException if there is an I/O error.
     */
    @Override
    public void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        userEmail = studentJwtService.extractUsername(jwt);
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails;
            boolean isTokenValid;
            if (studentTokenRepository.findByToken(jwt).isPresent()) {
                userDetails = studentUserDetailsService.loadUserByUsername(userEmail);
                isTokenValid = studentTokenRepository.findByToken(jwt)
                        .map(t -> !t.isExpired() && !t.isRevoked())
                        .orElse(false);
            } else {
                userDetails = ownerUserDetailsService.loadUserByUsername(userEmail);
                isTokenValid = ownerTokenRepository.findByToken(jwt)
                        .map(t -> !t.isExpired() && !t.isRevoked())
                        .orElse(false);
            }


            if (isJwtValid(jwt,userDetails) && isTokenValid) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        try {
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // Log the exception or handle it appropriately
        }

    }
    public boolean isJwtValid(String jwt, UserDetails userDetails) {
        return (studentJwtService.isTokenValid(jwt, userDetails) || ownerJwtService.isTokenValid(jwt, userDetails));
    }

}
