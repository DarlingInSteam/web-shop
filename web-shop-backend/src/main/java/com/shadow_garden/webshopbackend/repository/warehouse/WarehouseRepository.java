package com.shadow_garden.webshopbackend.repository.warehouse;

import com.shadow_garden.webshopbackend.entity.user.UserEntity;
import com.shadow_garden.webshopbackend.entity.warehouse.WarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WarehouseRepository extends JpaRepository<WarehouseEntity, Long> {
    Optional<WarehouseEntity> findById(long id);
}
