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

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final StudentJwtService studentJwtService;
    private final OwnerJwtService ownerJwtService;

    @Autowired
    private UserDetailsService studentUserDetailsService;

    @Autowired
    private UserDetailsService ownerUserDetailsService;

    private final StudentTokenRepository studentTokenRepository;
    private final OwnerTokenRepository ownerTokenRepository;

    @Override
    protected void doFilterInternal(
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

            if ((studentJwtService.isTokenValid(jwt, userDetails) || ownerJwtService.isTokenValid(jwt, userDetails)) && isTokenValid) {
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
        filterChain.doFilter(request, response);
    }
}
