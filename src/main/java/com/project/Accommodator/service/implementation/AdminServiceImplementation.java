package com.project.Accommodator.service.implementation;

import com.project.Accommodator.repository.AdminRepository;
import com.project.Accommodator.service.AdminService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImplementation implements AdminService {
    @Autowired
    AdminRepository adminRepository;

    public AdminServiceImplementation() {
        super();
    }
    @Transactional
    @Override
    public void approveStudentById(int id){
        adminRepository.approveStudentById(id);
    }

    @Transactional
    @Override
    public void rejectStudentById(int id){
        adminRepository.rejectStudentById(id);
    }
}
