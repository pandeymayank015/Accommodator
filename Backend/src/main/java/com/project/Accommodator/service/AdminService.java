package com.project.Accommodator.service;

import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

/**

 The AdminService interface defines the methods for performing administrative operations such as approving or rejecting a student.
 */
public interface AdminService {
     void approveStudentById(@PathVariable("id") int id);
     void rejectStudentById(@PathVariable("id") int id);
}
