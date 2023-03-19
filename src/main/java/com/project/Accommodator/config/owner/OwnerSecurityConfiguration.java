package com.project.Accommodator.config.owner;//package com.project.Accommodator.config.owner;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.AutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.logout.LogoutHandler;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//
//public class OwnerSecurityConfiguration {
//
//  private final OwnerJwtAuthenticationFilter jwtAuthFilter;
//  private final AuthenticationProvider authenticationProvider;
//
//
//
//  @Autowired
//  @Qualifier("ownerLogoutHandler")
//  private LogoutHandler logoutHandler;
//
//
//
//  @Bean
//  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    http
//        .csrf()
//        .disable()
//        .authorizeHttpRequests()
//        .requestMatchers("/owner/login")
//          .permitAll()
//            .requestMatchers("/owner/create")
//            .permitAll()
//        .anyRequest()
//          .authenticated()
//        .and()
//          .sessionManagement()
//          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        .and()
//        .authenticationProvider(authenticationProvider)
//        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//        .logout()
//        .logoutUrl("/owner/logout")
//        .addLogoutHandler(logoutHandler)
//        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
//    ;
//
//    return http.build();
//  }
//}
//
////package com.project.Accommodator.config.owner;
////
////import lombok.RequiredArgsConstructor;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.context.annotation.Primary;
////import org.springframework.security.authentication.AuthenticationProvider;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.config.http.SessionCreationPolicy;
////import org.springframework.security.core.context.SecurityContextHolder;
////import org.springframework.security.web.SecurityFilterChain;
////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
////import org.springframework.security.web.authentication.logout.LogoutHandler;
////
////@Configuration
////@EnableWebSecurity
////@RequiredArgsConstructor
////public class OwnerSecurityConfiguration {
////
////  private final OwnerJwtAuthenticationFilter jwtAuthFilter;
////  private final AuthenticationProvider authenticationProvider;
////
////
////
////  private final LogoutHandler logoutHandler;
////
////  @Bean
////  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////    http
////            .csrf()
////            .disable()
////            .authorizeHttpRequests()
////            .requestMatchers("/owner/login")
////            .permitAll()
////            .requestMatchers("/owner/create")
////            .permitAll()
////            .anyRequest()
////            .authenticated()
////            .and()
////            .sessionManagement()
////            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////            .and()
////            .authenticationProvider(authenticationProvider)
////            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
////            .logout()
////            .logoutUrl("/owner/logout")
////            .addLogoutHandler(logoutHandler)
////            .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
////    ;
////
////    return http.build();
////  }
////}
