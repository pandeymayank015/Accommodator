//package com.project.Accommodator.authentication;
//
//import com.project.Accommodator.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//import com.project.Accommodator.auth.owner.OwnerAuthenticationResponse;
//import com.project.Accommodator.auth.owner.OwnerDto;
//import com.project.Accommodator.model.Owner;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//public class OwnerAuthenticationResponseTest {
//
//    private OwnerAuthenticationResponse ownerAuthenticationResponse;
//
//    @BeforeEach
//    void setUp() {
//        Owner owner = Owner.builder()
//                .id(1L)
//                .firstName("John")
//                .lastName("Doe")
//                .email("john.doe@example.com")
//                .build();
//
//        ownerAuthenticationResponse = OwnerAuthenticationResponse.builder()
//                .token("abc123")
//                .owner(OwnerDto.fromEntity(owner))
//                .build();
//    }
//
//    @Test
//    void getToken_returnsToken() {
//        assertEquals("abc123", ownerAuthenticationResponse.getToken());
//    }
//
//    @Test
//    void getOwner_returnsOwnerDto() {
//        OwnerDto ownerDto = ownerAuthenticationResponse.getOwner();
//        assertNotNull(ownerDto);
//        assertEquals("John", ownerDto.getFirstName());
//        assertEquals("Doe", ownerDto.getLastName());
//        assertEquals("john.doe@example.com", ownerDto.getEmail());
//    }
//
//}
