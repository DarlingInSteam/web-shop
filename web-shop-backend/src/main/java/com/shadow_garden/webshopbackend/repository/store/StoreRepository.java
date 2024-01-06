package com.shadow_garden.webshopbackend.repository.store;

import com.shadow_garden.webshopbackend.entity.seller.SellerEntity;
import com.shadow_garden.webshopbackend.entity.store.StoreEntity;
import com.shadow_garden.webshopbackend.entity.warehouse.WarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
    Optional<StoreEntity> findById(long id);
    Optional<StoreEntity> findByWarehouse(WarehouseEntity entity);
}
