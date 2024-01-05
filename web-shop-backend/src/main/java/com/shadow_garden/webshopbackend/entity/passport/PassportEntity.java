package com.shadow_garden.webshopbackend.entity.passport;

import com.shadow_garden.webshopbackend.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="passport")
public class PassportEntity {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private int passport_series;

    private int passport_number;

    private String full_name;

    private int birth_year;

    private String registration_place;

    private String issued_by;
}
