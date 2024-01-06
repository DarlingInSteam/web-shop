package com.shadow_garden.webshopbackend.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SellProductRequest {
    private long productId;
    private long sellerId;
    private int quantity;
}
