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

/**

 Configuration class for the Owner application. This class defines various beans
 needed for the authentication and authorization process of the application.
 */
@Configuration
@RequiredArgsConstructor
public class OwnerApplicationConfig {

  /**

   An instance of the OwnerRepository interface used to retrieve the owner details from the database.
   */
  private final OwnerRepository repository;

  /**

   Defines a bean of the UserDetailsService interface that retrieves the user details
   from the database based on the username. The bean is used by the AuthenticationProvider to authenticate the user.
   @return an instance of UserDetailsService
   */
  @Bean
  public UserDetailsService ownerUserDetailsService() {
    return username -> repository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  /**

   Defines a bean of the AuthenticationProvider interface that uses the ownerUserDetailsService and passwordEncoder to authenticate the user.
   @return an instance of AuthenticationProvider
   */
  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(ownerUserDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  /**

   Defines a bean of the PasswordEncoder interface that is used to encode and decode the user password.
   @return an instance of PasswordEncoder
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**

   Defines a bean of the LogoutHandler interface that is used to handle logout requests from the owner.
   @return an instance of LogoutHandler
   */
  @Bean(name = "ownerLogoutHandler")
  public LogoutHandler ownerLogoutHandler() {
    return new OwnerLogoutHandler();

  }

  /**

   Defines a bean of the AuthenticationManager interface that is used to authenticate the owner.
   @return an instance of AuthenticationManager
   @throws Exception if there is an error getting the AuthenticationManager
   */
  @Primary
  @Bean(name = "ownerAuthenticationManager")
  public AuthenticationManager ownerAuthenticationManager(AuthenticationConfiguration config) throws Exception {
    return  new OwnerAuthenticationManager();
  }

}
