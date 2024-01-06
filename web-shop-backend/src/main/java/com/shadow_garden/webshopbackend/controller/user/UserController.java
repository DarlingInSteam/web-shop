package com.shadow_garden.webshopbackend.controller.user;

import com.shadow_garden.webshopbackend.dto.supplier.CreateSupplierRequest;
import com.shadow_garden.webshopbackend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserService service;

    @PostMapping("/all_users/{username}/settings")
    public ResponseEntity updateUsername(@PathVariable String username, @RequestParam String newUsername) {
        try {
            return ResponseEntity.ok(service.updateUsername(username, newUsername));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all_users/{username}")
    public ResponseEntity getUserByUsername(@PathVariable String username) {
        try {
            return ResponseEntity.ok(service.getUserByUsername(username));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all_users")
    public ResponseEntity getAllUsers() {
        try {
            return ResponseEntity.ok(service.getAllUsers());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
