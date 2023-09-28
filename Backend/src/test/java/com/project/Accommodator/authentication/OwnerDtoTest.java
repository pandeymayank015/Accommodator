package com.project.Accommodator.authentication;

import com.project.Accommodator.*;
import com.project.Accommodator.auth.owner.OwnerDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OwnerDtoTest {

    @Test
    void testConstructorAndGetters() {
        OwnerDto ownerDto = new OwnerDto(1, "john.doe@example.com", "John", "Doe", "555-5555", "Individual");
        assertEquals(1, ownerDto.getOwnerId());
        assertEquals("john.doe@example.com", ownerDto.getEmail());
        assertEquals("John", ownerDto.getFirstName());
        assertEquals("Doe", ownerDto.getLastName());
        assertEquals("555-5555", ownerDto.getContactNo());
        assertEquals("Individual", ownerDto.getOwnerType());
    }

    @Test
    void testSetters() {
        OwnerDto ownerDto = new OwnerDto();
        ownerDto.setOwnerId(1);
        ownerDto.setEmail("john.doe@example.com");
        ownerDto.setFirstName("John");
        ownerDto.setLastName("Doe");
        ownerDto.setContactNo("555-5555");
        ownerDto.setOwnerType("Individual");
        assertEquals(1, ownerDto.getOwnerId());
        assertEquals("john.doe@example.com", ownerDto.getEmail());
        assertEquals("John", ownerDto.getFirstName());
        assertEquals("Doe", ownerDto.getLastName());
        assertEquals("555-5555", ownerDto.getContactNo());
        assertEquals("Individual", ownerDto.getOwnerType());
    }

    @Test
    void testEqualsAndHashCode() {
        OwnerDto ownerDto1 = new OwnerDto(1, "john.doe@example.com", "John", "Doe", "555-5555", "Individual");
        OwnerDto ownerDto2 = new OwnerDto(1, "john.doe@example.com", "John", "Doe", "555-5555", "Individual");
        OwnerDto ownerDto3 = new OwnerDto(2, "jane.doe@example.com", "Jane", "Doe", "444-4444", "Corporate");
        assertEquals(ownerDto1, ownerDto2);
        assertNotEquals(ownerDto1, ownerDto3);
        assertEquals(ownerDto1.hashCode(), ownerDto2.hashCode());
        assertNotEquals(ownerDto1.hashCode(), ownerDto3.hashCode());
    }

    @Test
    void testToString() {
        OwnerDto ownerDto = new OwnerDto(1, "john.doe@example.com", "John", "Doe", "555-5555", "Individual");
        String expected = "OwnerDto(ownerId=1, email=john.doe@example.com, firstName=John, lastName=Doe, contactNo=555-5555, ownerType=Individual)";
        assertEquals(expected, ownerDto.toString());
    }
}
