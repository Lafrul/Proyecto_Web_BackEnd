package com.example.delahuerta.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/debug")
public class DebugController {
    
    @GetMapping("/whoami")
    public Map<String, Object> whoami(Authentication auth) {
        Map<String, Object> info = new HashMap<>();
        
        if (auth != null) {
            info.put("username", auth.getName());
            info.put("authenticated", auth.isAuthenticated());
            info.put("authorities", auth.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList()));
            info.put("principal", auth.getPrincipal().getClass().getName());
        } else {
            info.put("error", "No authentication found");
        }
        
        return info;
    }
    
    @PostMapping("/test-post")
    public Map<String, Object> testPost(@RequestBody Map<String, String> data, Authentication auth) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "POST funciona!");
        response.put("received", data);
        response.put("user", auth.getName());
        response.put("authorities", auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));
        return response;
    }
}