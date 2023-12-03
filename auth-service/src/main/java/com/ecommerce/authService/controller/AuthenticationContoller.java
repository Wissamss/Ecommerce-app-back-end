package com.ecommerce.authService.controller;

import com.ecommerce.authService.dto.AuthenticationRequest;
import com.ecommerce.authService.dto.AuthenticationResponse;
import com.ecommerce.authService.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationContoller {

    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationrequest){
        return ResponseEntity.ok(authenticationService.login(authenticationrequest));
    }

}
