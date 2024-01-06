package com.shadow_garden.webshopbackend.dto.supplier;

import com.shadow_garden.webshopbackend.entity.supplier.SupplierEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDto {
    private Long id;
    private String organizationName;
    private String address;
    private String directorName;
    private String directorPhone;
    private String bankName;
    private String checkingAccount;
    private String inn;

    public static SupplierDto toDto(SupplierEntity entity) {
        return SupplierDto
                .builder()
                .address(entity.getAddress())
                .id(entity.getId())
                .organizationName(entity.getOrganization_name())
                .directorName(entity.getDirector_name())
                .directorPhone(entity.getDirector_phone())
                .bankName(entity.getBank_name())
                .checkingAccount(entity.getChecking_account())
                .inn(entity.getInn())
                .build();
    }
}
