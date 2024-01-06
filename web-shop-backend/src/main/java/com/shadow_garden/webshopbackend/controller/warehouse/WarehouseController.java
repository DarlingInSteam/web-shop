package com.shadow_garden.webshopbackend.controller.warehouse;

import com.shadow_garden.webshopbackend.service.warehouse.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/warehouse")
@RequiredArgsConstructor
public class WarehouseController {
    @Autowired
    private final WarehouseService service;

    @PostMapping("/all_warehouses/create")
    public ResponseEntity createWarehouse(@RequestParam int stock) {
        try {
            return ResponseEntity.ok(service.createWarehouse(stock));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all_warehouses")
    public ResponseEntity getAllWarehouses() {
        try {
            return ResponseEntity.ok(service.getAllWarehouses());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all_warehouses/{id}")
    public ResponseEntity getWarehouse(@RequestParam long id) {
        try {
            return ResponseEntity.ok(service.getWarehouse(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
