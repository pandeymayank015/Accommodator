package com.project.Accommodator.config;
import com.project.Accommodator.config.owner.OwnerJwtAuthenticationFilter;
import com.project.Accommodator.config.student.StudentJwtAuthenticationFilter;
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

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final StudentJwtAuthenticationFilter studentJwtAuthFilter;
    private final OwnerJwtAuthenticationFilter ownerJwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Autowired
    @Qualifier("studentLogoutHandler")
    private LogoutHandler studentLogoutHandler;

    @Autowired
    @Qualifier("ownerLogoutHandler")
    private LogoutHandler ownerLogoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .cors()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/student/login", "/owner/login")
                .permitAll()
                .requestMatchers("/student/create", "/owner/create")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(studentJwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(ownerJwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/student/logout")
                .logoutUrl("/owner/logout")
                .addLogoutHandler(studentLogoutHandler)
                .addLogoutHandler(ownerLogoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
        ;

        return http.build();
    }
}
