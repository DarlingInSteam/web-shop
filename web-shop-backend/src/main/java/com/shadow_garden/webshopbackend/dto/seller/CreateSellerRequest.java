package com.shadow_garden.webshopbackend.dto.seller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateSellerRequest {
    private String username;
    private String name;
    private int salary;
    private String hireDate;
    private long storeId;
}
