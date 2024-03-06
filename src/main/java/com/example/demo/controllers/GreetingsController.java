package com.example.demo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping()
public class GreetingsController {

    @GetMapping
    public String index(Model model, Authentication user) {
        model.addAttribute("user", user);
        return String.format("%s", user);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello!");
    }

    @GetMapping("/bye")
    public ResponseEntity<String> sayGoodBye() {
        return ResponseEntity.ok("Good bye!");
    }

}
