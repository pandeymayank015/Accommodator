package com.project.Accommodator.auth.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class StudentDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Long contactNo;

}
