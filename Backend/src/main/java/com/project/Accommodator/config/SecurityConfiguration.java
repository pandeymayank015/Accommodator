//package com.project.Accommodator.config;
//import com.project.Accommodator.config.owner.OwnerJwtAuthenticationFilter;
//import com.project.Accommodator.config.student.StudentJwtAuthenticationFilter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.logout.LogoutHandler;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.Arrays;
//import java.util.Collections;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfiguration {
//
// private final StudentJwtAuthenticationFilter studentJwtAuthFilter;
// private final OwnerJwtAuthenticationFilter ownerJwtAuthFilter;
// private final AuthenticationProvider authenticationProvider;
//
// @Autowired
// @Qualifier("studentLogoutHandler")
// private LogoutHandler studentLogoutHandler;
//
// @Autowired
// @Qualifier("ownerLogoutHandler")
// private LogoutHandler ownerLogoutHandler;
//
//// @Bean
//// public CorsConfigurationSource corsConfigurationSource() {
//// CorsConfiguration configuration = new CorsConfiguration();
//// configuration.setAllowedOrigins(Collections.singletonList("*"));
//// configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//// configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
//// configuration.setAllowCredentials(true);
////
//// UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//// source.registerCorsConfiguration("/**", configuration);
////
//// return source;
//// }
//
// @Bean
// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
// http
// .cors()
// .and()
// .csrf()
// .disable()
// .authorizeHttpRequests()
// .requestMatchers("/student/login", "/owner/login")
// .permitAll()
// .requestMatchers("/student/create", "/owner/create")
// .permitAll()
// .anyRequest()
// .authenticated()
// .and()
// .sessionManagement()
// .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
// .and()
// .authenticationProvider(authenticationProvider)
// .addFilterBefore(studentJwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
// .addFilterBefore(ownerJwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
// .logout()
// .logoutUrl("/student/logout")
// .logoutUrl("/owner/logout")
// .addLogoutHandler(studentLogoutHandler)
// .addLogoutHandler(ownerLogoutHandler)
// .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
// ;
//
// return http.build();
// }
//}
package com.project.Accommodator.config;

import com.project.Accommodator.config.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    /**

     The JWT authentication filter used to extract and validate JWT tokens from incoming requests.
     */
    private final JwtAuthenticationFilter jwtAuthFilter;

    /**

     The authentication provider used to authenticate user credentials.
     */
    private final AuthenticationProvider authenticationProvider;

    /**

     The logout handler used to logout student users.
     */
    @Autowired
    @Qualifier("studentLogoutHandler")
    private LogoutHandler studentLogoutHandler;

    /**

     The logout handler used to logout owner users.
     */
    @Autowired
    @Qualifier("ownerLogoutHandler")
    private LogoutHandler ownerLogoutHandler;

    /**

     This method configures the security filter chain for the application.

     It configures the CORS policy, disables CSRF protection, defines the URL patterns to be allowed without authentication,

     sets the authentication provider and JWT authentication filter, and sets up the logout process with its handlers.

     @param http the HttpSecurity object used to configure the security

     @return a SecurityFilterChain object representing the configured security filter chain

     @throws Exception if an error occurs while configuring the security filter chain
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        var csrf= http
                .cors()
                .and()
                .csrf();
        var permitAll=csrf.disable()
                .authorizeHttpRequests()
                .requestMatchers("/student/login", "/owner/login")
                .permitAll();
        var sessionManagement=permitAll.requestMatchers("/student/create", "/owner/create")
                .permitAll()
                .anyRequest();
        var authenticated= sessionManagement.authenticated()
                .and()
                .sessionManagement();
        var logoutURL=authenticated.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider);
        var addFilterBefore= logoutURL.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/student/logout");
        var addLogoutHandler=addFilterBefore.logoutUrl("/owner/logout")
                .addLogoutHandler(studentLogoutHandler)
                .addLogoutHandler(ownerLogoutHandler);
        addLogoutHandler.logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
        ;
        return http.build();
    }
}
