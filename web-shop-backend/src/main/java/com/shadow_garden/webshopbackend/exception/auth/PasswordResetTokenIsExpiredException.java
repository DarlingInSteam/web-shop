package com.shadow_garden.webshopbackend.exception.auth;

public class PasswordResetTokenIsExpiredException extends Exception{
    public PasswordResetTokenIsExpiredException(String message) {
        super(message);
    }
}
