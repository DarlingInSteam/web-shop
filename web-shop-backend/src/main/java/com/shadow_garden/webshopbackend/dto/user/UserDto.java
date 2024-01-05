package com.shadow_garden.webshopbackend.dto.user;

import com.shadow_garden.webshopbackend.entity.user.UserEntity;
import com.shadow_garden.webshopbackend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;
    private String username;
    private Role role;

    public static UserDto toDto(UserEntity entity) {
        return UserDto
                .builder()
                .id(entity.getId())
                .role(entity.getRole())
                .username(entity.getUsername())
                .build();
    }
}
