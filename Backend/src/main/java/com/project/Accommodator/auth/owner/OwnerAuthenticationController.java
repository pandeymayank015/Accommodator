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

  /**

   The OwnerAuthenticationService object for handling owner authentication requests.
   */
  private final OwnerAuthenticationService service;

  /**

   This method is used to register an owner in the system.
   @param request The Owner object representing the owner information to be registered.
   @return ResponseEntity The HTTP response entity with the authentication response.
   */
  @PostMapping("/create")
  public ResponseEntity<OwnerAuthenticationResponse> register(
      @RequestBody Owner request

  ) {
    return ResponseEntity.ok(service.register(request));
  }
  /**

   This method is used to authenticate an owner in the system.
   @param request The OwnerAuthenticationRequest object representing the owner authentication request.
   @return ResponseEntity The HTTP response entity with the authentication response.
   */
  @PostMapping("/login")
  public ResponseEntity<OwnerAuthenticationResponse> authenticate(
      @RequestBody OwnerAuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }


}
