package com.project.Accommodator.controller;

import com.project.Accommodator.model.Owner;
import com.project.Accommodator.service.AdminService;
import com.project.Accommodator.service.FavoriteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AdminControllerTest {
    @Autowired
    private AdminController adminControllerMock;
    AdminService adminServiceMock;
    @BeforeEach
    public void setup(){
        adminServiceMock = mock(AdminService.class);
        adminControllerMock.adminService = adminServiceMock;
    }
    @Test
    public void objectCreated() throws Exception {
        assertNotNull(adminControllerMock);
    }
    @Test
    public void testApproveStudentById() throws Exception{
        int id = 1;
        doNothing().when(adminServiceMock).approveStudentById(id);
        adminControllerMock.approveStudentById(id);
    }
    @Test
    public void testRejectStudentById() throws Exception{
        int id = 1;
        doNothing().when(adminServiceMock).rejectStudentById(id);
        adminControllerMock.rejectStudentById(id);
    }
}