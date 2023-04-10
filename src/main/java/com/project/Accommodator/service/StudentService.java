package com.project.Accommodator.service;
import com.project.Accommodator.model.Student;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

/**
 * The StudentService interface provides methods to create, retrieve and manage students.
 */
public interface StudentService {

    Student getStudentById(int id);
//    Student loginStudent(String email,String password);

    Optional<Student> loginStudent(String email, String password);
    Iterable<Student> getAllStudents();

    Student createStudent(Student Student);

}
