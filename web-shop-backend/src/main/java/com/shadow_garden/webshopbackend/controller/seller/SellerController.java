package com.shadow_garden.webshopbackend.controller.seller;

import com.shadow_garden.webshopbackend.dto.product.CreateProductRequest;
import com.shadow_garden.webshopbackend.dto.seller.CreateSellerRequest;
import com.shadow_garden.webshopbackend.service.seller.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller")
@RequiredArgsConstructor
public class SellerController {
    @Autowired
    private final SellerService service;

    @PostMapping("/create")
    public ResponseEntity createSeller(@RequestBody CreateSellerRequest request) {
        try {
            return ResponseEntity.ok(service.createSeller(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getSeller(@PathVariable long id) {
        try {
            return ResponseEntity.ok(service.getSellerById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get_seller_by_user")
    public ResponseEntity getSellerByUser(@RequestParam String username) {
        try {
            return ResponseEntity.ok(service.getSellerByUser(username));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get_store")
    public ResponseEntity getStore(@RequestParam long id) {
        try {
            return ResponseEntity.ok(service.getStoreBySellerId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
