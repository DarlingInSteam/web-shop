package com.shadow_garden.webshopbackend.entity.supplier;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="supplier")
public class SupplierEntity {
    @Id
    @GeneratedValue
    private long id;

    private String organization_name;
    private String address;
    private String director_name;
    private String director_phone;
    private String bank_name;
    private String checking_account;
    private String inn;
}
