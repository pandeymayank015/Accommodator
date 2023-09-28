package com.project.Accommodator.auth.owner;

import com.project.Accommodator.config.owner.OwnerJwtService;
import com.project.Accommodator.model.Owner;
import com.project.Accommodator.repository.OwnerRepository;
import com.project.Accommodator.token.owner.OwnerToken;
import com.project.Accommodator.token.owner.OwnerTokenRepository;
import com.project.Accommodator.token.owner.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerAuthenticationService {
  private final OwnerRepository repository;
  private final OwnerTokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final OwnerJwtService jwtService;

  @Autowired
  @Qualifier("ownerAuthenticationManager")
  private AuthenticationManager authenticationManager;

  /**

   Registers a new owner by saving user data to the repository, generating a JWT token for the user, and saving the token to the token repository.
   @param request the Owner object containing user data to be registered.
   @return OwnerAuthenticationResponse containing the generated JWT token and owner information.
   */
  public OwnerAuthenticationResponse register(Owner request) {


    var userBuilder = Owner.builder();
    var firstName=userBuilder.firstName(request.getFirstName());
    var lastName=firstName.lastName(request.getLastName());
    var email=lastName.email(request.getEmail());
    var contact=email.contactNo(request.getContactNo());
    var ownerType=contact.ownerType(request.getOwnerType());

    var encodedPassword = ownerType.password(passwordEncoder.encode(request.getPassword()));
    var user = encodedPassword.build();


    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    saveUserToken(savedUser, jwtToken);
    OwnerDto ownerDto = new OwnerDto(savedUser.getOwnerId(), savedUser.getEmail(), savedUser.getFirstName(), savedUser.getLastName(), savedUser.getContactNo(), savedUser.getOwnerType());
    return OwnerAuthenticationResponse.builder()
            .token(jwtToken)
            .owner(ownerDto)
            .build();
  }

  /**

   Authenticates an owner by verifying the user credentials, generating a new JWT token, and revoking all the valid tokens for the user.
   @param request the OwnerAuthenticationRequest object containing the user credentials to be authenticated.
   @return OwnerAuthenticationResponse containing the generated JWT token and owner information.
   */
  public OwnerAuthenticationResponse authenticate(OwnerAuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    OwnerDto ownerDto = new OwnerDto(user.getOwnerId(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getContactNo(), user.getOwnerType());
    return OwnerAuthenticationResponse.builder()
            .token(jwtToken)
            .owner(ownerDto)
            .build();
  }

  /**

   Saves the user token to the token repository.
   @param user the Owner object representing the user.
   @param jwtToken the JWT token generated for the user.
   */

  private void saveUserToken(Owner user, String jwtToken) {

    var tokenBuilder = OwnerToken.builder();
    var userData=tokenBuilder.user(user);
    var tokenData=userData.token(jwtToken);
    var tokenType=tokenData.tokenType(TokenType.BEARER);
    var expired=tokenType.expired(false);
    var revoked=expired.revoked(false);
    var token=revoked.build();
  tokenRepository.save(token);
  }

  /**
   * Revokes all valid tokens associated with the given owner user by setting them as expired and revoked in the token repository.
   *
   * @param user the owner user whose tokens are to be revoked
   */
  private void revokeAllUserTokens(Owner user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getOwnerId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }
}
