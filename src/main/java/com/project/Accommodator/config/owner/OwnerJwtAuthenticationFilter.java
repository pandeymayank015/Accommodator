//package com.project.Accommodator.config.owner;
//
//import com.project.Accommodator.token.owner.OwnerTokenRepository;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.lang.NonNull;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//public class OwnerJwtAuthenticationFilter extends OncePerRequestFilter {
//
//  private final OwnerJwtService jwtService;
//
//  @Autowired
//  private UserDetailsService ownerUserDetailsService;
//
//  private final OwnerTokenRepository tokenRepository;
//
//  @Override
//  protected void doFilterInternal(
//      @NonNull HttpServletRequest request,
//      @NonNull HttpServletResponse response,
//      @NonNull FilterChain filterChain
//  ) throws ServletException, IOException {
//    final String authHeader = request.getHeader("Authorization");
//    final String jwt;
//    final String userEmail;
//    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
//      filterChain.doFilter(request, response);
//      return;
//    }
//    jwt = authHeader.substring(7);
//    userEmail = jwtService.extractUsername(jwt);
//    System.out.println(SecurityContextHolder.getContext().getAuthentication());
//    if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//      UserDetails userDetails = this.ownerUserDetailsService.loadUserByUsername(userEmail);
//      var isTokenValid = tokenRepository.findByToken(jwt)
//          .map(t -> !t.isExpired() && !t.isRevoked())
//          .orElse(false);
//      if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
//        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//            userDetails,
//            null,
//            userDetails.getAuthorities()
//        );
//        authToken.setDetails(
//            new WebAuthenticationDetailsSource().buildDetails(request)
//        );
//        SecurityContextHolder.getContext().setAuthentication(authToken);
//      }
//    }
//    System.out.println(SecurityContextHolder.getContext().getAuthentication());
//    filterChain.doFilter(request, response);
//  }
//}
