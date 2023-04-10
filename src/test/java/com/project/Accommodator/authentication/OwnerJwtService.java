package com.project.Accommodator.authentication;


import com.project.Accommodator.config.owner.OwnerJwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class OwnerJwtServiceTest {

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private OwnerJwtService ownerJwtService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testExtractUsername() {
        String token = ownerJwtService.generateToken(userDetails);
        String username = ownerJwtService.extractUsername(token);

        assertEquals(userDetails.getUsername(), username);
    }

    @Test
    void testExtractClaim() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("key1", "value1");
        claims.put("key2", 123);

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(ownerJwtService.getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

        String value1 = ownerJwtService.extractClaim(token, c -> c.get("key1", String.class));
        int value2 = ownerJwtService.extractClaim(token, c -> c.get("key2", Integer.class));

        assertEquals("value1", value1);
        assertEquals(123, value2);
    }









}
