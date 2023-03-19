//package com.project.Accommodator.controller;
//import com.project.Accommodator.model.Student;
//import com.project.Accommodator.service.StudentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.Map;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/student")
//public class StudentController {
//    @Autowired
//    StudentService studentService;
//    @CrossOrigin
//    @GetMapping("/get/{id}")
//    public Student getStudentById(@PathVariable("id") int id) {
//        return studentService.getStudentById(id);
//    }
//    @CrossOrigin
//    @PostMapping(value = "/create", consumes = {"multipart/form-data"})
//    public void createStudent(@RequestParam String firstName,@RequestParam String lastName,@RequestParam String email,@RequestParam String password,@RequestParam Long contactNo, @RequestParam("offerLetter") MultipartFile pdfFile) throws IOException {
//         studentService.createStudent(firstName,lastName,email,password,contactNo,pdfFile);
//    }
//
//
//    @CrossOrigin
//    @PostMapping("/login")
//    public Optional<Student> loginStudent(@RequestBody Map<String, String> credentials) {
//        String email = credentials.get("email");
//        String password = credentials.get("password");
//        return studentService.loginStudent(email, password);
//    }
//
//
//    @CrossOrigin
//    @GetMapping("/get/all")
//    public Iterable<Student> getAllStudents() {
//        return studentService.getAllStudents();
//    }
//}
