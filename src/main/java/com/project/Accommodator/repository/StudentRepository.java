package com.project.Accommodator.repository;

import com.project.Accommodator.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Integer> {
    @Query("SELECT s FROM Student s WHERE s.email = ?1 and s.password = ?2 and s.isApproved = 1")
     Student findByEmail(String email,String password);

    Optional<Student> findByEmail(String email);

    @Query("SELECT s FROM Student s WHERE s.isApproved = 0 and s.isRevoked = 0")
    Iterable<Student> getAllStudents();
}
