package com.example.delahuerta.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Map;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/user")
public class ProfileController {
  @GetMapping("/profile")
  public Map<String,String> profile(Authentication auth) {
    return Map.of("user", auth.getName(), "msg","Perfil visible con USER o ADMIN");
  }
}
