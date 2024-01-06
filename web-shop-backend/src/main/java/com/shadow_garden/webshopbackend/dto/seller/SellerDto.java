package com.shadow_garden.webshopbackend.dto.seller;

import com.shadow_garden.webshopbackend.entity.seller.SellerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SellerDto {
    private long id;
    private long userId;
    private String name;
    private int salary;
    private String hireDate;

    public static SellerDto toDto(SellerEntity entity) {
        return SellerDto
                .builder()
                .id(entity.getId())
                .hireDate(entity.getHireDate())
                .name(entity.getName())
                .salary(entity.getSalary())
                .build();
    }
}
