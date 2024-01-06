package com.shadow_garden.webshopbackend.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
    private long supplierId;
    private String productName;
    private int quantityInStock;
    private int price;
    private long warehouseId;
}
