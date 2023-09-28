package com.project.Accommodator.config.owner;

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

 Service class for handling JWT (JSON Web Token) related operations for the Owner user type.
 */
@Service
public class OwnerJwtService {

  private static final String SECRET_KEY = "586E3272357538782F413F4428472B4B6250655368566B597033733676397924";

  /**

   Extracts the username from the given JWT token.
   @param token the JWT token from which the username is to be extracted.
   @return the username associated with the token.
   */
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  /**

   Extracts a claim from the given JWT token using the provided claims resolver function.
   @param token the JWT token from which the claim is to be extracted.
   @param claimsResolver the function that resolves the claim from the token's claims.
   @param <T> the type of the claim to be extracted.
   @return the claim of the specified type extracted from the token.
   */
  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  /**

   Generates a new JWT token for the provided Owner user details.
   @param userDetails the details of the Owner user for whom the token is to be generated.
   @return a new JWT token for the specified user.
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

   Checks whether the given JWT token is valid for the specified Owner user details.
   @param token the JWT token to be validated.
   @param userDetails the details of the Owner user for whom the token is to be validated.
   @return true if the token is valid for the specified user, false otherwise.
   */
  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }

  public boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private Claims extractAllClaims(String token) {
    return Jwts
        .parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  public Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
