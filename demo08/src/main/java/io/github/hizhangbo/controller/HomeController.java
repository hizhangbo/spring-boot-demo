package io.github.hizhangbo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ResponseEntity<String> index() {
        System.out.println("thread: " + Thread.currentThread().getName());
        return ResponseEntity.ok("index");
    }
}
