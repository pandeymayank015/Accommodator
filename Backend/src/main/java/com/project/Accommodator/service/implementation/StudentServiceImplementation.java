package com.project.Accommodator.service.implementation;

import com.project.Accommodator.model.Student;
import com.project.Accommodator.service.StudentService;
import com.project.Accommodator.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

/**

 The StudentServiceImplementation class implements the StudentService interface and provides methods

 to perform CRUD operations on Student entities.
 */
@Service
public class StudentServiceImplementation implements StudentService {
    @Autowired
    StudentRepository StudentRepository;

    public StudentServiceImplementation() {
        super();
    }

    /**

     Retrieves a Student object with the given id.
     @param id The id of the student to retrieve.
     @return The Student object with the given id.
     */
    @Override
    public Student getStudentById(int id) {
        Student Student = StudentRepository.findById(id).orElse(null);
        return Student;
    }
    @Override
    public Iterable<Student> getAllStudents() {
        Iterable<Student> Student = StudentRepository.getAllStudents();
        return Student;
    }

    /**

     Creates a new Student object.
     @param Student student The Student object to create.
     @return The newly created Student object.
     */
    @Override
    public Student createStudent(Student Student) {
        return StudentRepository.save(Student);
    }


    /**

     Logs in a student with the given email and password.
     @param email The email address of the student.
     @param password The password of the student.
     @return An Optional object containing the logged in Student object if login is successful, otherwise empty.
     */
    @Override
    public Optional<Student> loginStudent(String email, String password){
        Optional<Student> stu = StudentRepository.findByEmail(email);
        if (stu == null) {
            try {
                throw new Exception("Student not found");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else
            return stu;
    }
}