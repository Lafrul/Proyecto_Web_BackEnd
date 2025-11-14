package com.example.delahuerta.controller;

import com.example.delahuerta.repository.UserRepository;
import com.example.delahuerta.security.jwt.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Agregar CrossOrigin adicional
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        
        System.out.println("🔐 Login request received for user: " + loginRequest.get("username"));
        
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwt = jwtUtils.generateToken(userDetails.getUsername());

            Map<String, Object> response = new HashMap<>();
            response.put("token", jwt);
            response.put("username", userDetails.getUsername());

            userRepository.findByUsername(username)
                    .ifPresent(user -> response.put("role", user.getRole()));

            System.out.println("✅ Login successful for: " + username);
            return ResponseEntity.ok(response);

        } catch (org.springframework.security.core.AuthenticationException ex) {
            System.err.println("❌ Login failed for: " + username + " - " + ex.getMessage());
            Map<String, String> error = new HashMap<>();
            error.put("error", "Credenciales inválidas");
            return ResponseEntity.status(401).body(error);
        }
    }
}