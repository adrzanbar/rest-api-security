package com.uncode.rest_api_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.uncode.rest_api_security.model.AuthenticationRequest;
import com.uncode.rest_api_security.model.AuthenticationResponse;
import com.uncode.rest_api_security.model.User;
import com.uncode.rest_api_security.model.Role; // Assuming you have a Role enum
import com.uncode.rest_api_security.repository.UserRepository;
import com.uncode.rest_api_security.service.CustomUserDetailsService;
import com.uncode.rest_api_security.util.JwtUtil;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Set default role if not already set
        if (user.getRole() == null) {
            user.setRole(Role.USER); // Default to USER role
        }

        userRepository.save(user);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()));

            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails);

            return ResponseEntity
                    .ok(new AuthenticationResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}
