package com.project.Accommodator.token.student;

import com.project.Accommodator.model.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**

 This class represents the authentication token for a student user.
 It contains the token itself, its type, and the user it belongs to.
 It also has fields to track if the token is revoked or expired.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "StudentToken")
public class StudentToken {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer id;

  @Column(unique = true)
  public String token;

  @Enumerated(EnumType.STRING)
  public TokenType tokenType = TokenType.BEARER;

  public boolean revoked;

  public boolean expired;

  @ManyToOne
  @JoinColumn(name = "student_Id")
  public Student user;

  @Override
  public String toString() {
    return "StudentToken{" +
            "id=" + id +
            ", token='" + token + '\'' +
            ", tokenType=" + tokenType +
            ", revoked=" + revoked +
            ", expired=" + expired +
            '}';
  }

}
