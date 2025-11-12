package com.example.delahuerta.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
  @GetMapping("/secret")
  public Map<String,String> secret() {
    return Map.of("msg","Solo ADMIN ve esto");
  }
}