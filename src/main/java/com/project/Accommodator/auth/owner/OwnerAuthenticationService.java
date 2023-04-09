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
