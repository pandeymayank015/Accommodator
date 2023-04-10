package com.project.Accommodator.model;

import com.project.Accommodator.token.owner.OwnerToken;
import com.project.Accommodator.token.owner.TokenType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class OwnerTokenTest {
    private OwnerToken ownerToken;
    private Owner owner;

    @BeforeEach
    public void setUp() {
        owner = new Owner();
        owner.setOwnerId(1);
        ownerToken = new OwnerToken();
        ownerToken.setId(1);
        ownerToken.setToken("sample_token");
        ownerToken.setTokenType(TokenType.BEARER);
        ownerToken.setRevoked(false);
        ownerToken.setExpired(false);
        ownerToken.setUser(owner);
    }

    @Test
    public void testGetters() {
        assertEquals(1, ownerToken.getId());
        assertEquals("sample_token", ownerToken.getToken());
        assertEquals(TokenType.BEARER, ownerToken.getTokenType());
        assertFalse(ownerToken.isRevoked());
        assertFalse(ownerToken.isExpired());
        assertEquals(owner, ownerToken.getUser());
    }
}
