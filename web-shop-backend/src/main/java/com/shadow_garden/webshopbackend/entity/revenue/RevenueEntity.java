package com.shadow_garden.webshopbackend.entity.revenue;

import com.shadow_garden.webshopbackend.entity.seller.SellerEntity;
import com.shadow_garden.webshopbackend.entity.store.StoreEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="revenue")
public class RevenueEntity {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(name = "seller_id")
    private SellerEntity seller;

    @OneToOne
    @JoinColumn(name = "store_id")
    private StoreEntity store;

    private int revenue_amount;

    private String sale_date;
}
