package com.shadow_garden.webshopbackend.repository.seller;

import com.shadow_garden.webshopbackend.entity.seller.SellerEntity;
import com.shadow_garden.webshopbackend.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<SellerEntity, Long> {
    Optional<SellerEntity> findById(long id);
}
