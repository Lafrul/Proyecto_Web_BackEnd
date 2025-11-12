package com.example.delahuerta.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.delahuerta.model.User;
import com.example.delahuerta.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public User createUser(@RequestBody User u) {
    return userService.createUser(u);
  }

  @GetMapping
  public List<User> all() {
    return userService.getAllUsers();
  }
}