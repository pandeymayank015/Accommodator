package com.project.Accommodator.config.owner;
import com.project.Accommodator.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
public class OwnerApplicationConfig {

  private final OwnerRepository repository;

  @Bean
  public UserDetailsService ownerUserDetailsService() {
    return username -> repository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(ownerUserDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

//  @Bean
//  @Primary
//  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//    return config.getAuthenticationManager();
//  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean(name = "ownerLogoutHandler")
  public LogoutHandler ownerLogoutHandler() {
    return new OwnerLogoutHandler();

  }

  @Primary
  @Bean(name = "ownerAuthenticationManager")
  public AuthenticationManager ownerAuthenticationManager(AuthenticationConfiguration config) throws Exception {
    return  new OwnerAuthenticationManager();
  }

}
