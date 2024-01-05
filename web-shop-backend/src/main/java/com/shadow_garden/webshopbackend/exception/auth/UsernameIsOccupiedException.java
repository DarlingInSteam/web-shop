package com.shadow_garden.webshopbackend.exception.auth;

public class UsernameIsOccupiedException extends Exception {
    public UsernameIsOccupiedException(String message) {
        super(message);
    }
}
