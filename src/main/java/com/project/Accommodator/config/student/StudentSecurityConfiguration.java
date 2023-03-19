package com.project.Accommodator.config.student;//package com.project.Accommodator.config.student;
//
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
//public class StudentSecurityConfiguration {
//
//  private final StudentJwtAuthenticationFilter jwtAuthFilter;
//  private final AuthenticationProvider authenticationProvider;
//
//
//
//  @Autowired
//  @Qualifier("studentLogoutHandler")
//  private LogoutHandler logoutHandler;
//  @Bean
//  public SecurityFilterChain studentSecurityFilterChain(HttpSecurity http) throws Exception {
//    http
//            .cors().and()
//        .csrf()
//        .disable()
//        .authorizeHttpRequests()
//        .requestMatchers("/student/login")
//          .permitAll()
//            .requestMatchers("/student/create")
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
//        .logoutUrl("/student/logout")
//        .addLogoutHandler(logoutHandler)
//        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
//    ;
//
//    return http.build();
//  }
//}
