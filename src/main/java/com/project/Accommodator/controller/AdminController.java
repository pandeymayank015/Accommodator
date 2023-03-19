package com.project.Accommodator.controller;
import com.project.Accommodator.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @CrossOrigin
    @PutMapping("/approve/{id}")
    public void approveStudentById(@PathVariable("id") int id) {
         adminService.approveStudentById(id);
    }
    @CrossOrigin
    @PutMapping("/reject/{id}")
    public void rejectStudentById(@PathVariable("id") int id) {
        adminService.rejectStudentById(id);
    }
}
