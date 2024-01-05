package com.shadow_garden.webshopbackend.entity.warehouse;

import com.shadow_garden.webshopbackend.entity.product.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="warehouse")
public class WarehouseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "quantity_in_stock")
    private int quantityInStock;

    @OneToMany
    @JoinTable(
            name = "products_in_warehouse",
            joinColumns = @JoinColumn(name = "warehouse_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<ProductEntity> productEntities;
}
