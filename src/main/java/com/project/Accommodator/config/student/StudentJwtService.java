package com.project.Accommodator.config.student;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**

 A service for managing JWT (JSON Web Token) for Student users.
 It provides methods to generate, extract and validate JWTs.
 */
@Service
public class StudentJwtService {

  private static final String SECRET_KEY = "586E3272357538782F413F4428472B4B6250655368566B597033733676397924";

  /**

   Extracts the username from the given JWT.
   @param token the JWT from which to extract the username
   @return the username extracted from the JWT
   */
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  /**

   Extracts the claim from the given JWT using the provided function.
   @param token the JWT from which to extract the claim
   @param claimsResolver the function to use to extract the claim
   @param <T> the type of the claim
   @return the extracted claim
   */
  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  /**
   Generates a JWT for the given user.
   @param userDetails the user for which to generate the JWT
   @return the generated JWT
   */

  public String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }

  public String generateToken(
      Map<String, Object> extraClaims,
      UserDetails userDetails
  ) {
    var builder=Jwts.builder();
    var setClaims=builder.setClaims(extraClaims);
    var setSubject=setClaims.setSubject(userDetails.getUsername());
    var setIssuedAt=setSubject.setIssuedAt(new Date(System.currentTimeMillis()));
    var setExpiration=setIssuedAt.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24));
    var signWith=setExpiration.signWith(getSignInKey(), SignatureAlgorithm.HS256);
    return signWith.compact();
  }

  /**

   Validates the given JWT for the given user details.
   @param token the JWT to validate
   @param userDetails the user details to validate against
   @return true if the JWT is valid for the given user details, false otherwise
   */
  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }

  /**

   Checks if the given JWT is expired.
   @param token the JWT to check
   @return true if the JWT is expired, false otherwise
   */
  public boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  /**

   Extracts all claims from the given JWT.
   @param token the JWT from which to extract the claims
   @return the claims extracted from the JWT
   */
  private Claims extractAllClaims(String token) {
    return Jwts
        .parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  /**

   A private helper method to generate a Key object for signing JWT tokens.
   Decodes the secret key string using Base64 and creates a HMAC-SHA key from it.
   @return the Key object for signing JWT tokens
   */
  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
