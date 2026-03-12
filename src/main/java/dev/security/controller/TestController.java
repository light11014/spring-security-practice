package dev.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/customer/test")
    public String customerTest() {
        return "customer access";
    }

    @GetMapping("/owner/test")
    public String ownerTest() {
        return "owner access";
    }

    @GetMapping("/admin/test")
    public String adminTest() {
        return "admin access";
    }
}