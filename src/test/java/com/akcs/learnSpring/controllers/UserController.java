package com.akcs.learnSpring.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {
  @GetMapping
  public String greet() {
    return "Hello, welcome to the platform.";
  }
}
