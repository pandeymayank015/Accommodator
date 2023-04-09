package com.project.Accommodator.service.implementation;

import com.project.Accommodator.model.Student;
import com.project.Accommodator.service.StudentService;
import com.project.Accommodator.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StudentServiceImplementation implements StudentService {
    @Autowired
    StudentRepository StudentRepository;

    public StudentServiceImplementation() {
        super();
    }

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

    @Override
    public Student createStudent(Student Student) {
        return StudentRepository.save(Student);
    }


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