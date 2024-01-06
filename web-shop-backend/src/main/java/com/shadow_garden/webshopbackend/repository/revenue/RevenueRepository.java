package com.shadow_garden.webshopbackend.repository.revenue;

import com.shadow_garden.webshopbackend.entity.product.ProductEntity;
import com.shadow_garden.webshopbackend.entity.revenue.RevenueEntity;
import com.shadow_garden.webshopbackend.entity.seller.SellerEntity;
import com.shadow_garden.webshopbackend.entity.store.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RevenueRepository extends JpaRepository<RevenueEntity, Long> {
    Optional<RevenueEntity> findById(long id);
    Optional<RevenueEntity> findBySeller(SellerEntity seller);
    Optional<RevenueEntity> findByStore(StoreEntity store);
}
