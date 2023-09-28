package com.project.Accommodator.config.student;
import com.project.Accommodator.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutHandler;

/**
 * Configuration class for the Student application.
 */
@Configuration
@RequiredArgsConstructor
public class StudentApplicationConfig {


  /**
   * The repository for the Student entity.
   */
  private final StudentRepository repository;

  /**
   * Provides an implementation of the UserDetailsService interface for Student users.
   * @return The UserDetailsService for Student users.
   */
  @Bean
  public UserDetailsService studentUserDetailsService() {
    return username -> repository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  /**
   * Provides an AuthenticationProvider for Student users.
   * @return An AuthenticationProvider for Student users.
   */
  @Bean
  public AuthenticationProvider studentAuthenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(studentUserDetailsService());
    authProvider.setPasswordEncoder(studentPasswordEncoder());
    return authProvider;
  }

  /**
   * Provides a PasswordEncoder for Student passwords.
   * @return A PasswordEncoder for Student passwords.
   */
  @Bean
  public PasswordEncoder studentPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * Provides a LogoutHandler for Student logouts.
   * @return A LogoutHandler for Student logouts.
   */
  @Bean(name = "studentLogoutHandler")
  public LogoutHandler StudentLogoutHandler() {
    return new StudentLogoutHandler();

  }


  /**
   * Provides an AuthenticationManager for Student authentication.
   * @param config The authentication configuration.
   * @return An AuthenticationManager for Student authentication.
   * @throws Exception If an error occurs while creating the AuthenticationManager.
   */
  @Bean(name = "studentAuthenticationManager")
  public AuthenticationManager studentAuthenticationManager(AuthenticationConfiguration config) throws Exception {
    return new StudentAuthenticationManager();
  }

}
