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
    var user = Owner.builder()
        .firstName(request.getFirstName())
        .lastName(request.getLastName())
        .email(request.getEmail())
            .contactNo(request.getContactNo())
            .ownerType(request.getOwnerType())
        .password(passwordEncoder.encode(request.getPassword()))
        .build();
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    saveUserToken(savedUser, jwtToken);
    return OwnerAuthenticationResponse.builder()
        .token(jwtToken)
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
    return OwnerAuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  private void saveUserToken(Owner user, String jwtToken) {
    var token = OwnerToken.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
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
