package com.project.Accommodator.service.implementation;

import com.project.Accommodator.repository.AdminRepository;
import com.project.Accommodator.service.AdminService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**

 Service implementation for performing admin operations
 */
@Service
public class AdminServiceImplementation implements AdminService {

    /**

     Repository for performing admin operations
     */
    @Autowired
    AdminRepository adminRepository;

    /**

     Default constructor
     */
    public AdminServiceImplementation() {
        super();
    }

    /**

     Approve a student with the given id
     @param id The id of the student to be approved
     */
    @Transactional
    @Override
    public void approveStudentById(int id){
        adminRepository.approveStudentById(id);
    }

    /**

     Reject a student with the given id
     @param id The id of the student to be rejected
     */
    @Transactional
    @Override
    public void rejectStudentById(int id){
        adminRepository.rejectStudentById(id);
    }
}
