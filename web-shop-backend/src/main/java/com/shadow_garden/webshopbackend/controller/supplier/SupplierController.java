package com.shadow_garden.webshopbackend.controller.supplier;

import com.shadow_garden.webshopbackend.dto.store.CreateStoreRequest;
import com.shadow_garden.webshopbackend.dto.supplier.CreateSupplierRequest;
import com.shadow_garden.webshopbackend.service.supplier.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/supplier")
@RequiredArgsConstructor
public class SupplierController {
    @Autowired
    private final SupplierService service;

    @PostMapping("/create")
    public ResponseEntity createStore(@RequestBody CreateSupplierRequest request) {
        try {
            return ResponseEntity.ok(service.createSupplier(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all_suppliers/{id}")
    public ResponseEntity getSupplier(@PathVariable long id) {
        try {
            return ResponseEntity.ok(service.getSupplier(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all_suppliers")
    public ResponseEntity getAllSuppliers() {
        try {
            return ResponseEntity.ok(service.getAllSuppliers());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
