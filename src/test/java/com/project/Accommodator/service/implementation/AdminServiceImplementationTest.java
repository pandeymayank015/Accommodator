package com.project.Accommodator.service.implementation;

import com.project.Accommodator.repository.AdminRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdminServiceImplementationTest {
    @Mock
    private AdminRepository adminRepositoryMock;

    @InjectMocks
    private AdminServiceImplementation adminServiceImplementationMock;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testApproveStudentById(){
        int id = 1;
        adminServiceImplementationMock.approveStudentById(id);
    }
    @Test
    public void testRejectStudentById(){
        int id = 1;
        adminServiceImplementationMock.rejectStudentById(id);
    }
}