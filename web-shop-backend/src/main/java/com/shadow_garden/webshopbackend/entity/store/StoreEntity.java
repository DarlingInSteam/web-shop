package com.shadow_garden.webshopbackend.entity.store;

import com.shadow_garden.webshopbackend.entity.seller.SellerEntity;
import com.shadow_garden.webshopbackend.entity.user.UserEntity;
import com.shadow_garden.webshopbackend.entity.warehouse.WarehouseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="store")
public class StoreEntity {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(name = "warehouse_id")
    private WarehouseEntity warehouse;

    @OneToMany
    @JoinTable(
            name = "sellers_in_store",
            joinColumns = @JoinColumn(name = "store_id"),
            inverseJoinColumns = @JoinColumn(name = "seller_id")
    )
    private Set<SellerEntity> sellerEntities;

    @Column(name = "store_name")
    private String storeName;

    private String location;
}
