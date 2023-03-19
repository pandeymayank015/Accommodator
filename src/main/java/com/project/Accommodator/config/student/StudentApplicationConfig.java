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

@Configuration
@RequiredArgsConstructor
public class StudentApplicationConfig {

  private final StudentRepository repository;

  @Bean
  public UserDetailsService studentUserDetailsService() {
    return username -> repository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  @Bean
  public AuthenticationProvider studentAuthenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(studentUserDetailsService());
    authProvider.setPasswordEncoder(studentPasswordEncoder());
    return authProvider;
  }

  @Bean
  public PasswordEncoder studentPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean(name = "studentLogoutHandler")
  public LogoutHandler StudentLogoutHandler() {
    return new StudentLogoutHandler();

  }

  @Bean(name = "studentAuthenticationManager")
  public AuthenticationManager studentAuthenticationManager(AuthenticationConfiguration config) throws Exception {
    return new StudentAuthenticationManager();
  }

}
