package com.ecommerce.authService.controller;

import com.ecommerce.authService.dto.AuthenticationRequest;
import com.ecommerce.authService.dto.AuthenticationResponse;
import com.ecommerce.authService.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationContoller {

    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationrequest){
        return ResponseEntity.ok(authenticationService.login(authenticationrequest));
    }

}
