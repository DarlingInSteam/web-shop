package com.shadow_garden.webshopbackend.controller.store;

import com.shadow_garden.webshopbackend.dto.seller.CreateSellerRequest;
import com.shadow_garden.webshopbackend.dto.store.CreateStoreRequest;
import com.shadow_garden.webshopbackend.service.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {
    @Autowired
    private final StoreService service;

    @PostMapping("/create")
    public ResponseEntity createStore(@RequestBody CreateStoreRequest request) {
        try {
            return ResponseEntity.ok(service.createStore(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all_stores/{id}/sellers")
    public ResponseEntity getAllSellers(@PathVariable long id) {
        try {
            return ResponseEntity.ok(service.getAllSellers(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all_stores")
    public ResponseEntity getAllStores() {
        try {
            return ResponseEntity.ok(service.getAllStores());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all_stores/{id}")
    public ResponseEntity getStore(@PathVariable long id) {
        try {
            return ResponseEntity.ok(service.getStore(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all_stores/{id}/products")
    public ResponseEntity getAllProducts(@PathVariable long id) {
        try {
            return ResponseEntity.ok(service.getAllProductsInStore(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
