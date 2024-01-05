package com.shadow_garden.webshopbackend.exception.auth;

public class PasswordResetTokenNotFoundException extends Exception {
    public PasswordResetTokenNotFoundException(String message) {
        super(message);
    }
}
