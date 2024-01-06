package com.shadow_garden.webshopbackend.repository.supplier;

import com.shadow_garden.webshopbackend.entity.store.StoreEntity;
import com.shadow_garden.webshopbackend.entity.supplier.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupplierRepository extends JpaRepository<SupplierEntity, Long> {
    Optional<SupplierEntity> findById(long id);
}
