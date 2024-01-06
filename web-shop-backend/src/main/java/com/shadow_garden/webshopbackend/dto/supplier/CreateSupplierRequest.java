package com.shadow_garden.webshopbackend.dto.supplier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateSupplierRequest {
    private String organizationName;
    private String address;
    private String directorName;
    private String directorPhone;
    private String bankName;
    private String checkingAccount;
    private String inn;
}
