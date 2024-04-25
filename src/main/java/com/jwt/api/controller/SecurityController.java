package com.jwt.api.controller;

import com.jwt.api.entity.*;
import com.jwt.api.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.*;

@RestController
public class SecurityController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/home")
    public String welcome() {
        return "Welcome to Springoot-jwd demo !";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Incorrect username or password", ex);
        } catch (Exception ex){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Error in the request", ex);
        }

        final String jwt = jwtUtil.generateToken(authenticationRequest.getUserName());
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
