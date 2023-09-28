package com.project.Accommodator.repository;

import com.project.Accommodator.model.Admin;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer> {
    @Modifying
    @Query(value = "UPDATE Student s SET isApproved = 1 WHERE s.studentId = ?1",nativeQuery = true)
     void approveStudentById(int id);
    @Modifying
    @Query(value = "UPDATE Student s SET isRevoked = 1 WHERE s.studentId = ?1",nativeQuery = true)
    void rejectStudentById(int id);
}


