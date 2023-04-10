package com.project.Accommodator.controller;
import com.project.Accommodator.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * This class is the REST controller for admin-related endpoints. It provides
 * HTTP endpoints for approving and rejecting student registration requests.
 * Endpoints are prefixed with "/admin".
 */
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    /**
     * Approves a student registration request by its ID.
     *
     * @param id The ID of the student to approve.
     */
    @CrossOrigin
    @PutMapping("/approve/{id}")
    public void approveStudentById(@PathVariable("id") int id) {
         adminService.approveStudentById(id);
    }
    /**
     * Rejects a student registration request by its ID.
     *
     * @param id The ID of the student to reject.
     */
    @CrossOrigin
    @PutMapping("/reject/{id}")
    public void rejectStudentById(@PathVariable("id") int id) {
        adminService.rejectStudentById(id);
    }
}
