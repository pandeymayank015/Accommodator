//package com.project.Accommodator.auth.student;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//public class StudentAuthenticationResponse {
//
//  private String token;
//}
//
//
package com.project.Accommodator.auth.student;

import com.project.Accommodator.model.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentAuthenticationResponse {

  private String token;
  private StudentDto student;

}
