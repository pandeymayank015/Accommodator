package com.project.Accommodator.service.implementation;

import com.project.Accommodator.model.Owner;
import com.project.Accommodator.repository.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class OwnerServiceImplementationTest {
    @Mock
    private OwnerRepository ownerRepositoryMock;

    @InjectMocks
    private OwnerServiceImplementation ownerServiceImplementationMock;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testCreateOwner() {
        Owner expectedOwner = new Owner(12);
        when(ownerRepositoryMock.save(expectedOwner)).thenReturn(expectedOwner);
        Owner actualOwner = ownerServiceImplementationMock.createOwner(expectedOwner);
        assertEquals(expectedOwner, actualOwner);
    }

    @Test
    public void testGetOwnerById() {
        int id = 1;
        Owner expectedOwner = new Owner(1);
        when(ownerRepositoryMock.findById(id)).thenReturn(Optional.of(expectedOwner));
        Owner actualOwner = ownerServiceImplementationMock.getOwnerById(id);
        assertEquals(expectedOwner, actualOwner);
    }

}