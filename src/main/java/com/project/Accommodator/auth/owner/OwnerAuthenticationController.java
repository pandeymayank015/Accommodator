package com.project.Accommodator.auth.owner;

import com.project.Accommodator.model.Owner;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner")
@RequiredArgsConstructor
@CrossOrigin
public class OwnerAuthenticationController {

  private final OwnerAuthenticationService service;

  @PostMapping("/create")
  public ResponseEntity<OwnerAuthenticationResponse> register(
      @RequestBody Owner request

  ) {
    return ResponseEntity.ok(service.register(request));
  }
  @PostMapping("/login")
  public ResponseEntity<OwnerAuthenticationResponse> authenticate(
      @RequestBody OwnerAuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }


}
