package com.shadow_garden.webshopbackend.dto.warehouse;

import com.shadow_garden.webshopbackend.entity.warehouse.WarehouseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseDto {
    private long id;
    private int quantityInStock;

    public static WarehouseDto toDto(WarehouseEntity entity) {
        return WarehouseDto
                .builder()
                .id(entity.getId())
                .quantityInStock(entity.getQuantityInStock())
                .build();
    }
}
