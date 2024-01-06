package com.shadow_garden.webshopbackend.dto.product;

import com.shadow_garden.webshopbackend.entity.product.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private long id;
    private long supplierId;
    private String productName;
    private int quantityInStock;
    private int price;
    private long warehouseId;

    public static ProductDto toDto(ProductEntity entity) {
        return ProductDto
                .builder()
                .id(entity.getId())
                .productName(entity.getProduct_name())
                .price(entity.getPrice())
                .quantityInStock(entity.getQuantity_in_stock())
                .supplierId(entity.getSupplier().getId())
                .warehouseId(entity.getWarehouse().getId())
                .build();
    }
}
