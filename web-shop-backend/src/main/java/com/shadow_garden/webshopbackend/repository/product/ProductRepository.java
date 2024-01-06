package com.shadow_garden.webshopbackend.repository.product;

import com.shadow_garden.webshopbackend.entity.product.ProductEntity;
import com.shadow_garden.webshopbackend.entity.seller.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findById(long id);
}
