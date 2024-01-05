package com.shadow_garden.webshopbackend.entity.seller;

import com.shadow_garden.webshopbackend.entity.store.StoreEntity;
import com.shadow_garden.webshopbackend.entity.user.UserEntity;
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
@Table(name="seller")
public class SellerEntity {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String name;

    private Integer salary;

    @Column(name = "hire_date")
    private String hireDate;

    @ManyToOne
    private StoreEntity storeEntity;
}
