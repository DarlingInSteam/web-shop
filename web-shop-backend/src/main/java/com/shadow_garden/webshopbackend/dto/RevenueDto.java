package com.shadow_garden.webshopbackend.dto;

import com.shadow_garden.webshopbackend.entity.revenue.RevenueEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RevenueDto {
    private long id;
    private long sellerId;
    private long storeId;
    private int revenueAmount;
    private String saleDate;

    public static RevenueDto toDto(RevenueEntity entity) {
        return RevenueDto
                .builder()
                .id(entity.getId())
                .revenueAmount(entity.getRevenue_amount())
                .saleDate(entity.getSale_date())
                .sellerId(entity.getSeller().getId())
                .storeId(entity.getStore().getId())
                .build();
    }
}
