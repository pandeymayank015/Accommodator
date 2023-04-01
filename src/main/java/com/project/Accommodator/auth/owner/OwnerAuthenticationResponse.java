package com.project.Accommodator.auth.owner;

import com.project.Accommodator.model.Owner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OwnerAuthenticationResponse {

  private String token;
  private OwnerDto owner;

}
