package com.shadow_garden.webshopbackend.dto.store;

import com.shadow_garden.webshopbackend.entity.store.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto {
    private long id;
    private long warehouseId;
    private String storeName;
    private String location;

    public static StoreDto toDto(StoreEntity entity) {
        return StoreDto
                .builder()
                .id(entity.getId())
                .warehouseId(builder().warehouseId)
                .location(entity.getLocation())
                .storeName(entity.getStoreName())
                .build();
    }
}
