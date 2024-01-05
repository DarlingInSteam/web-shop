package com.shadow_garden.webshopbackend.entity.product;

import com.shadow_garden.webshopbackend.entity.supplier.SupplierEntity;
import com.shadow_garden.webshopbackend.entity.warehouse.WarehouseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product")
public class ProductEntity {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplier;

    @ManyToOne
    private WarehouseEntity warehouse;

    private String product_name;

    private int quantity_in_stock;

    private int price;
}
