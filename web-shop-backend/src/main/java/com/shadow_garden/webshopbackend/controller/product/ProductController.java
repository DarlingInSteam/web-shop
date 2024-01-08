package com.shadow_garden.webshopbackend.controller.product;

import com.shadow_garden.webshopbackend.dto.product.CreateProductRequest;
import com.shadow_garden.webshopbackend.dto.product.SellProductRequest;
import com.shadow_garden.webshopbackend.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    private final ProductService service;

    @PostMapping("/create")
    public ResponseEntity createProduct(@RequestBody CreateProductRequest request) {
        try {
            return ResponseEntity.ok(service.createProduct(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/sell_product")
    public ResponseEntity sellProduct(@RequestBody SellProductRequest request) {
        try {
            return ResponseEntity.ok(service.sellProduct(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getProduct(@PathVariable long id) {
        try {
            return ResponseEntity.ok(service.getProduct(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity getAllProduct() {
        try {
            return ResponseEntity.ok(service.getAllProduct());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/add_count")

    public ResponseEntity addProduct(@PathVariable long id, @RequestParam int stock) {
        try {
            return ResponseEntity.ok(service.addNewStockInProduct(stock, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
