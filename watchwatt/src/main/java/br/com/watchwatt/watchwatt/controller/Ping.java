package br.com.watchwatt.watchwatt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public record Ping() implements Controller {

    @GetMapping()
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }
}
