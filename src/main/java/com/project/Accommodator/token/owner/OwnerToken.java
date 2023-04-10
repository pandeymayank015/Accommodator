package com.project.Accommodator.token.owner;

import com.project.Accommodator.model.Owner;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a token for an owner, used for authentication.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OwnerToken")
public class OwnerToken {

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
  @JoinColumn(name = "owner_Id")
  public Owner user;

  @Override
  public String toString() {
    return "OwnerToken{" +
            "id=" + id +
            ", token='" + token + '\'' +
            ", tokenType=" + tokenType +
            ", revoked=" + revoked +
            ", expired=" + expired +
            '}';
  }

}
