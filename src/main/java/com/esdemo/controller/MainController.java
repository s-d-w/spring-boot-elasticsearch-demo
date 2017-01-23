package com.esdemo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    
    @RequestMapping("/")
    String home() {
        return "Hello from esdemo!";
    }
}
