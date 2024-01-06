package com.shadow_garden.webshopbackend.service.user;

import com.shadow_garden.webshopbackend.dto.user.UserDto;
import com.shadow_garden.webshopbackend.exception.user.UserNotFoundException;
import com.shadow_garden.webshopbackend.repository.user.UserRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public String updateUsername(@NotNull String username, @NotNull String newUsername) throws UserNotFoundException {
        var user = repository
                .findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setUsername(newUsername);

        repository.save(user);

        return "Username updated";
    }

    public UserDto getUserByUsername(@NotNull String username) throws UserNotFoundException {
        return UserDto.toDto(repository
                .findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"))
        );
    }

    public List<UserDto> getAllUsers() {
        var users = repository.findAll();

        return users
                .stream()
                .map(UserDto::toDto)
                .toList();
    }


}
