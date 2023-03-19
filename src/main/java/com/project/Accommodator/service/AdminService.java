package com.project.Accommodator.service;

import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

public interface AdminService {
     void approveStudentById(@PathVariable("id") int id);
     void rejectStudentById(@PathVariable("id") int id);
}
